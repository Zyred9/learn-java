package com.example.learn;

import com.example.learn.LearnJavaApplication;
import com.example.learn.es.Book;
import com.example.learn.es.EsService;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnJavaApplication.class)
public class EsTest {

	@Autowired
	private EsService bookService;
	
	@Test
	public void testAll() throws InterruptedException {
		t1AddOne();
		t2AddBatch();
		Thread.sleep(1000);
		t3FindAll();
		t4search();
		t5deleteOne();
		t6deleteBatch();
		Thread.sleep(1000);
		t7FindAll();
		
	}

	@Test
	public void t1AddOne() {
		IndexResponse putOne = bookService.putOne(new Book(2, "西游记"));
		System.out.println("【1】putOne：" + putOne);
	}

	@Test
	public void t2AddBatch() {
		List<Book> list = new ArrayList<>();
		list.add(new Book(2, "水浒传"));
		list.add(new Book(3, "三国演义"));
		BulkResponse putBatch = bookService.putBatch(list);
		System.out.println("【2】putBatch：" + putBatch.status());
	}

	@Test
	public void t3FindAll() {
		System.out.println("【3】");
		List<Book> res = bookService.getAll();
		System.out.println("↓↓↓findAll");
		res.forEach(System.out::println);
		System.out.println("↑↑↑findAll");
	}

	@Test
	public void t4search() {
		System.out.println("【4】");
		List<Book> searchByKey = bookService.searchByKey("水传");
		searchByKey.forEach(System.out::println);

		Book book = bookService.getByBookId(2);
		System.out.println("【4】getByBookId：" + book);
	}

	@Test
	public void t5deleteOne() {
		BulkByScrollResponse deleteById = bookService.deleteById(1);
		System.out.println("【5】deleteById：" + deleteById.getStatus());
	}

	@Test
	public void t6deleteBatch() {
		List<Integer> ids = new ArrayList<>();
		ids.add(2);
		ids.add(3);
		BulkResponse deleteBatch = bookService.deleteBatch(ids);
		System.out.println("【6】deleteBatch：" + deleteBatch.status());
	}

	@Test
	public void t7FindAll() {
		System.out.println("【7】");
		List<Book> res = bookService.getAll();
		System.out.println("↓↓↓findAll");
		res.forEach(System.out::println);
		System.out.println("↑↑↑findAll");
	}
}