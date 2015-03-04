package com.spring.service;

import com.spring.model.PetDto;
import com.spring.persistence.dao.CategoryDao;
import com.spring.persistence.dao.PetDao;
import com.spring.persistence.domain.Category;
import com.spring.persistence.domain.Pet;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
@Service
public class PetServiceImpl implements PetService {

    static Logger log = Logger.getLogger(PetServiceImpl.class);

    @Autowired
    private PetDao petDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<PetDto> getAllPetsDtos() {
        List<PetDto> resultList = new ArrayList<>();
        for (Pet p :petDao.getAllPets()){
            resultList.add(new PetDto(p));
        }
        return resultList;
    }

    @Override
    @Transactional
    public int parseSite(String path) throws IOException{
        categoryDao.clear();
        List<Category> categories =  parseCategories(path);
        for (Category cat : categories){
            cat.setId(categoryDao.store(cat));
        }
        for (Category cat : categories){
            log.warn(cat.getId());
        }

        return 555;
    }
    


    private List<Category>  parseCategories (String path) throws IOException {
        List<Category> categories = new ArrayList<>();
        Document  doc = Jsoup.connect(path)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                .get();
        Elements cats = doc.getElementsByClass("cat");
        for (Element cat : cats) {
            if (cat.parent().parent().children().size() > 1 && cat.previousElementSibling().tag().toString().equals("img") ){
                String sale = cat.parent().parent().child(1).child(0).getElementsByTag("a").text();
                String name = cat.text();
                String url = cat.attr("href");
                log.warn(sale+", "+name+", "+url);
                Category newCategory = new Category();
                newCategory.setName(name);
                newCategory.setUrl(url);
                newCategory.setEnable(true);
                categories.add(newCategory);
            }
        }
        return categories;
    }


}
