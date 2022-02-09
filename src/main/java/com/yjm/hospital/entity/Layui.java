package com.yjm.hospital.entity;

import java.util.HashMap;
import java.util.List;

public class Layui extends HashMap<String, Object> {
    public static Layui data(Integer count, List<?> data) {
        Layui layui = new Layui();
        layui.put("code", 0);
        layui.put("msg", "");
        layui.put("count", count);
        layui.put("data", data);
        return layui;
    }
}
