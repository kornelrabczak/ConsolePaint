package com.thecookiezen.bussiness.boundary;

import com.thecookiezen.bussiness.control.Canvas;

public interface Printable {
    void execute(Canvas canvas, Printer printer);
}
