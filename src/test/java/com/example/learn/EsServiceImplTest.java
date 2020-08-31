package com.example.learn;

import com.example.learn.es.EsService;
import com.example.learn.es.QueryPage;
import com.example.learn.es.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EsServiceImplTest {

    @Autowired
    private EsService esService;

    @Test
    public void insert() {
        List<Student> students = new ArrayList<>();
        for (int i = 10; i <= 12; i++) {
            Student student = new Student();
            student.setId(i + "").setAge(10 + i).setName("王二狗" + i).setScore(72.5 + i).setInfo("大王派我来巡山" + i);
            students.add(student);
        }
        esService.addAll(students);
    }

    @Test
    public void fuzzySearch() {
        QueryPage queryPage = new QueryPage();
        queryPage.setCurrent(1).setSize(5);
        Page<Student> list = esService.search("二狗2", queryPage);
        list.forEach(System.out::println);
    }
}
