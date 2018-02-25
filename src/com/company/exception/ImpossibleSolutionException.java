package com.company.exception;

/**
 * Created by Eric on 04/09/2015.
 */
public class ImpossibleSolutionException extends Exception{
    public ImpossibleSolutionException(){
        super();
    }

    public ImpossibleSolutionException(String msg){
        super(msg);
    }
}
