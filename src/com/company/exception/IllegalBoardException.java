package com.company.exception;

/**
 * Created by Eric on 03/09/2015.
 */
public class IllegalBoardException extends Exception {
    public IllegalBoardException(){
        super();
    }

    public IllegalBoardException(String msg){
        super(msg);
    }
}
