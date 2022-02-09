package com.yjm.hospital.base.dto;

public class StatusCode {
    public static final int OK = 20000;//成功
    public static final String OKMESSAGE = "%s成功";//成功
    public static final int ERROR = 20001;// 失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int REPERROR = 20005;//重复操作
    public static final int EXISTERROR = 20006;//数据存在判断
    public static final String EXISTMESSAGE = "%s重复，请修改！";//数据存在判断
    public static final int USEERROR = 20007;//数据使用中
    public static final String USEMESSAGE = "%s数据在%s使用中，不能删除！";//数据存在判断
    public static final int GENERATORERROR = 20008;//渲染模板失败
    public static final String GENERATORMESSAGE = "渲染模板失败，表名：%s";//渲染模板失败
    public static final int COVER = 20009;//数据重复，是否覆盖
    public static final int BUSINESSOCCURRED = 20010;//所选上级已发生业务，是否结转
    public static final int IMPORTERROR = 20011;//导入报错提示响应码
    public static final int MANUALERROR = 21000;// 失败  需手动关闭弹框
}
