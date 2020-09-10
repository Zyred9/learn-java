package com.example.learn.design.patterns.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *      经理，也是员工，所以实现员工接口
 * </p>
 *
 * @author zyred
 * @createTime 2020/9/10 13:52
 **/
public class Leader implements IEmployee {

    /** 经理应该知道自己员工谁擅长什么节能，所以应该将员工的信息加载到经理内部 **/
    private Map<String, IEmployee> employeeMap = new HashMap<>();

    public Leader() {
        employeeMap.put(EmployeeA.goodAt, new EmployeeA());
        employeeMap.put(EmployeeB.goodAt, new EmployeeB());
    }

    @Override
    public void doing(String task) {
        if (this.employeeMap.containsKey(task)){
            IEmployee employee = this.employeeMap.get(task);
            employee.doing(task);
        }
    }
}
