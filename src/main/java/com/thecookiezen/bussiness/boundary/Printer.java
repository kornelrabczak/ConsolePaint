package com.thecookiezen.bussiness.boundary;

import com.thecookiezen.bussiness.control.Canvas;

public interface Printer {

    void print(Canvas canvas);
    void print(String message);
    void println(String message);
}
