package com.rockwellcollins.atc.agree.analysis.handlers;


public class VerifySingleHandler extends VerifyHandler {
    @Override
    protected boolean isRecursive() {
       return false;
    }

    @Override
    protected String getJobName() {
        return "AGREE - Verify Single Layer";
    }
}
