package com.example.oopproject.global;

import java.util.ArrayList;
import java.util.List;

import com.example.oopproject.model.Product;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
