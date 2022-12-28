package com.wang.example.springbootdatajpa.test.extents_strategy.single_table;

import com.wang.example.springbootdatajpa.WindowFileService;
import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.Document;
import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.Folder;
import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFile;
import com.wang.example.springbootdatajpa.entity.extents_strategy.single_table.WindowFileVo;
import com.wang.example.springbootdatajpa.repository.extents_strategy.single_table.DocumentRepository;
import com.wang.example.springbootdatajpa.repository.extents_strategy.single_table.FolderRepository;
import com.wang.example.springbootdatajpa.repository.extents_strategy.single_table.WindowFileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/26 18:33
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StartegyTest {
    @Autowired
    private WindowFileRepository windowFileRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private WindowFileService windowFileService;


    @Test
    public void insertParent() {
        WindowFile windowFile = WindowFile.builder()
                .date(new Date())
                .name("windowFile")
                .type("windwoFile type")
                .build();
        System.out.println(windowFile);
        WindowFile save = windowFileRepository.save(windowFile);
        System.out.println("-------");
        System.out.println(save);


    }

    @Test
    public void insertFolder() {

        Folder folder = new Folder();
        folder.setFileCount(11);
        folder.setDate(new Date());
        folder.setName("folder");
        folder.setType("folder type");

        System.out.println(folder);
        Folder save = folderRepository.save(folder);
        System.out.println("----------------------");
        System.out.println(save);
    }

    @Test
    public void insertDocument() {
        Document document = new Document();
        document.setSize("12");
        document.setDate(new Date());
        document.setName("document");
        document.setType("document type");
        System.out.println(document);
        System.out.println("-------------------------------");
        Document save = documentRepository.save(document);
        System.out.println(save);
    }


    @Test
    public void selectTest() {
        Optional<WindowFile> byId = windowFileRepository.findById(13);
        System.out.println(byId.get());
        System.out.println("----------");
        List<WindowFile> all = windowFileRepository.findAll();
        System.out.println(all);
        System.out.println("--------------");
        Optional<Folder> byId1 = folderRepository.findById(14);
        System.out.println(byId1.get());
        System.out.println("-------------------");
        List<Folder> all1 = folderRepository.findAll();
        System.out.println(all1);
        System.out.println("----------------");
        Optional<Document> byId2 = documentRepository.findById(15);
        System.out.println(byId2.get());
        System.out.println("---------------");
        List<Document> all2 = documentRepository.findAll();
        System.out.println(all2);
        /**
         * Hibernate: select windowfile0_.id as id2_18_0_, windowfile0_.date as date3_18_0_, windowfile0_.name as name4_18_0_, windowfile0_.type as type5_18_0_, windowfile0_.size as size6_18_0_, windowfile0_.file_count as file_cou7_18_0_, windowfile0_.discriminator as discrimi1_18_0_ from window_file windowfile0_ where windowfile0_.id=?
         * WindowFile(id=13, name=windowFile, type=windwoFile type, date=2022-11-26 18:38:06.774)
         * ----------
         * Hibernate: select windowfile0_.id as id2_18_, windowfile0_.date as date3_18_, windowfile0_.name as name4_18_, windowfile0_.type as type5_18_, windowfile0_.size as size6_18_, windowfile0_.file_count as file_cou7_18_, windowfile0_.discriminator as discrimi1_18_ from window_file windowfile0_
         * [WindowFile(id=13, name=windowFile, type=windwoFile type, date=2022-11-26 18:38:06.774), WindowFile(id=14, name=folder, type=folder type, date=2022-11-26 18:47:07.301)Folder{fileCount=11}, WindowFile(id=15, name=document, type=document type, date=2022-11-26 21:16:31.149)Document{size='12'}]
         * --------------
         * Hibernate: select folder0_.id as id2_18_0_, folder0_.date as date3_18_0_, folder0_.name as name4_18_0_, folder0_.type as type5_18_0_, folder0_.file_count as file_cou7_18_0_ from window_file folder0_ where folder0_.id=? and folder0_.discriminator='Folder'
         * WindowFile(id=14, name=folder, type=folder type, date=2022-11-26 18:47:07.301)Folder{fileCount=11}
         * -------------------
         * Hibernate: select folder0_.id as id2_18_, folder0_.date as date3_18_, folder0_.name as name4_18_, folder0_.type as type5_18_, folder0_.file_count as file_cou7_18_ from window_file folder0_ where folder0_.discriminator='Folder'
         * [WindowFile(id=14, name=folder, type=folder type, date=2022-11-26 18:47:07.301)Folder{fileCount=11}]
         * ----------------
         * Hibernate: select document0_.id as id2_18_0_, document0_.date as date3_18_0_, document0_.name as name4_18_0_, document0_.type as type5_18_0_, document0_.size as size6_18_0_ from window_file document0_ where document0_.id=? and document0_.discriminator='Document'
         * WindowFile(id=15, name=document, type=document type, date=2022-11-26 21:16:31.149)Document{size='12'}
         * ---------------
         * Hibernate: select document0_.id as id2_18_, document0_.date as date3_18_, document0_.name as name4_18_, document0_.type as type5_18_, document0_.size as size6_18_ from window_file document0_ where document0_.discriminator='Document'
         * [WindowFile(id=15, name=document, type=document type, date=2022-11-26 21:16:31.149)Document{size='12'}]
         */

        /**
         * 结论
         * 父类会查询所有的
         * 子类只会查询自己的类型的，它会自动在sql中加上
         * folder0_.discriminator='Folder'
         * document0_.discriminator='Document'
         *
         * 而且自己试着通过方法名称的方式构造查询条件时，辨别的字段是没有的
         */

    }

    /**
     * 没成功
     */
    @Test
    public void SelectTest02() {

        List<WindowFileVo> byDiscriminator = windowFileRepository.getByDiscriminator("Document");
        System.out.println(byDiscriminator);


        /*List<WindowFile> windowFile = windowFileRepository.findByDiscriminator("WindowFile");
        System.out.println(windowFile);*/

//        List<WindowFileVo> byType = windowFileRepository.getByType("windwoFile type");
//        System.out.println(byType);

    }

    @Test
    public void selectTest03() {
        List<WindowFileVo> document = windowFileService.getByDiscriminator("Document");
        System.out.println(document);

    }



}
