package com.intern.common.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class SystemLog implements Serializable {
    String sysModule;
    String sysType;
    String sysDesc;
    String sysParam;
    String sysResponse;
    String sysMethod;
    String sysIp;
    Date sysDate;
}
