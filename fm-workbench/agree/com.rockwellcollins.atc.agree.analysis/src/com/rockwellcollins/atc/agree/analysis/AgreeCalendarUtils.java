package com.rockwellcollins.atc.agree.analysis;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IfThenElseExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.NodeCallExpr;
import jkind.lustre.SubrangeIntType;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

public class AgreeCalendarUtils {

    static private String dfaName;
    
    static public Node getDFANode(String name, int synchrony){
        if(synchrony <= 0){
            throw new AgreeException("Attempt to use quasi-synchrony of value: "+synchrony);
        }
        
        dfaName = name;
        
        VarDecl rVar = new VarDecl("_r", 
                new SubrangeIntType(BigInteger.valueOf(-synchrony), 
                        BigInteger.valueOf(synchrony)));
        IdExpr r = new IdExpr(rVar.id);
        
        VarDecl rIsBoundedVar = new VarDecl("_r_is_bounded", NamedType.BOOL);
        IdExpr rIsBounded = new IdExpr(rIsBoundedVar.id);
        
        VarDecl outVar = new VarDecl("_out", NamedType.BOOL);
        IdExpr out = new IdExpr(outVar.id);
        
        VarDecl clkVar0 = new VarDecl("_clk0", NamedType.BOOL);
        IdExpr clk0 = new IdExpr(clkVar0.id);
        
        VarDecl clkVar1 = new VarDecl("_clk1", NamedType.BOOL);
        IdExpr clk1 = new IdExpr(clkVar1.id);
        
        List<VarDecl> inputs = new ArrayList<>();
        inputs.add(clkVar0);
        inputs.add(clkVar1);
        
        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(outVar);
        
        List<VarDecl> locals = new ArrayList<>();
        locals.add(rVar);
        locals.add(rIsBoundedVar);
        
        Expr intZeroExpr = new IntExpr(BigInteger.ZERO);
        Expr intOneExpr = new IntExpr(BigInteger.ONE);
        Expr intNegOneExpr = new IntExpr(BigInteger.valueOf(-1));
        Expr intSyncValExpr = new IntExpr(BigInteger.valueOf(synchrony));
        Expr intNegSyncValxpr = new IntExpr(BigInteger.valueOf(-synchrony));
        
        //(0 -> pre r)
        Expr rPreExpr = new BinaryExpr(new IntExpr(BigInteger.ZERO), BinaryOp.ARROW, new UnaryExpr(UnaryOp.PRE, r));
        //(0 -> pre r) < 0
        Expr rPreLTExpr = new BinaryExpr(rPreExpr, BinaryOp.LESS, intZeroExpr);
        //(0 -> pre r) > 0
        Expr rPreGTExpr = new BinaryExpr(rPreExpr, BinaryOp.GREATER, intZeroExpr);
        //(0 -> pre r) + 1
        Expr rPrePlus = new BinaryExpr(rPreExpr, BinaryOp.PLUS, intOneExpr);
        //(0 -> pre r) - 1
        Expr rPreMinus = new BinaryExpr(rPreExpr, BinaryOp.MINUS, intOneExpr);        
        //if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1)
        Expr ifExpr0 = new IfThenElseExpr(rPreLTExpr, intOneExpr, rPrePlus);
        //if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)
        Expr ifExpr1 = new IfThenElseExpr(rPreGTExpr, intNegOneExpr, rPreMinus);
        //if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1)) else (0 -> pre r)
        Expr ifExprClk1 = new IfThenElseExpr(clk1, ifExpr1, rPreExpr);
        //if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        //else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        //else (0 -> pre r);
        Expr ifExprClk0 = new IfThenElseExpr(clk0, ifExpr0, ifExprClk1);
        //if p and q then 0 
        //else if p then (if (0 -> pre r) < 0 then 1 else ((0 -> pre r) + 1))
        //else if q then (if (0 -> pre r) > 0 then -1 else ((0 -> pre r) - 1))
        //else (0 -> pre r);
        Expr rExpr = new IfThenElseExpr(new BinaryExpr(clk0, BinaryOp.AND, clk1), intZeroExpr, ifExprClk0);
        
        //((0 -> pre r) = 2 and p)
        Expr condExpr0 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.EQUAL, intSyncValExpr), BinaryOp.AND, clk0);
        //((0 -> pre r) = -2 and q)
        Expr condExpr1 = new BinaryExpr(new BinaryExpr(rPreExpr, BinaryOp.EQUAL, intNegSyncValxpr), BinaryOp.AND, clk1);
        //not (((0 -> pre r) = 2 and p) or ((0 -> pre r) = -2 and q))
        Expr outExpr = new UnaryExpr(UnaryOp.NOT, new BinaryExpr(condExpr0, BinaryOp.OR, condExpr1));
        
        //r <= 2 and r >= -2
        Expr rIsBoundedExpr = new BinaryExpr(new BinaryExpr(rExpr, BinaryOp.LESSEQUAL, intSyncValExpr),
                BinaryOp.OR,
                new BinaryExpr(rExpr, BinaryOp.GREATEREQUAL, intNegSyncValxpr));
        
        List<Equation> equations = new ArrayList<>();
        equations.add(new Equation(r, rExpr));
        equations.add(new Equation(rIsBounded, rIsBoundedExpr));
        equations.add(new Equation(out, outExpr));
        
        List<String> properties = new ArrayList<>();
        properties.add(rIsBounded.id);
        
        return new Node(dfaName, inputs, outputs, locals, equations, properties);
    }
    
    static public Node getCalendarNode(String name, List<IdExpr> clks){
        
        Node calendarNode;
        Expr nodeExpr = null;
        IdExpr outVar = new IdExpr("_out");
        
        for(int i = 0; i < clks.size()-1; i++){
            Expr clk0 = clks.get(i);
            for(int j = i+1; j < clks.size(); j++){
                Expr clk1 = clks.get(j);
                Expr dfaExpr = DFAExpr(clk0, clk1);
                if(nodeExpr == null){
                    nodeExpr = dfaExpr;
                }else{
                    nodeExpr = new BinaryExpr(nodeExpr, BinaryOp.AND, dfaExpr);
                }
            }
        }
        
        //make the inputs and outputs
        List<VarDecl> inputs = new ArrayList<>();
        
        for(IdExpr clkId : clks){
            inputs.add(new VarDecl(clkId.id, NamedType.BOOL));
        }
        
        List<VarDecl> outputs = new ArrayList<>();
        outputs.add(new VarDecl(outVar.id, NamedType.BOOL));
        
        Equation nodeEq = new Equation(outVar, nodeExpr);
        calendarNode = new Node(name, inputs, outputs, new ArrayList<VarDecl>(), Collections.singletonList(nodeEq));
        
        return calendarNode;
    }
    
    static private Expr DFAExpr(Expr clk0, Expr clk1){
        return new NodeCallExpr(dfaName, clk0, clk1);
    }
    
}