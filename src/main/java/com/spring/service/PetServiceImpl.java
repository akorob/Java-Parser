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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
@Service
public class PetServiceImpl implements PetService {

    static Logger log = Logger.getLogger(PetServiceImpl.class);

    public static final String SITE_PATH = "http://www.zoo-zoo.ru/";
    public static final String SALE_PATH = "sale/";
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    @Autowired
    private PetDao petDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public long count() {
        return petDao.count();
    }

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
    public int parseSite() throws IOException{
        petDao.clear();
        categoryDao.clear();
        List<Category> categories =  parseMainForCategories(SITE_PATH);
        for (Category cat : categories){
            cat.setId(categoryDao.store(cat));
        }
        List<Pet> siteList = new ArrayList<>();
        for (Category cat : categories){
            List<Pet> categoryList = parseCategoryForPets(cat);
            siteList.addAll(categoryList);
        }

        return siteList.size();
    }


    private List<Pet> parseCategoryForPets (Category category) throws IOException{
        List<Pet> categoryList = new ArrayList<>();
        String categoryPath = SITE_PATH + category.getUrl() + SALE_PATH;
        int pagesCount = getPagesCountInCategory(categoryPath);
        for (int i = 0; i < pagesCount; i++){
            String path = categoryPath + "page" + i + ".html";
            List<Pet> pageList = parsePageForPets(path);
            categoryList.addAll(pageList);
        }

        for (Pet pet : categoryList){
            pet.setCategory(category);
            petDao.store(pet);
        }

        return  categoryList;
    }


    private List<Pet> parsePageForPets(String pagePath) throws IOException{
        Document  doc = Jsoup.connect(pagePath)
                .userAgent(USER_AGENT)
                .get();
        Elements offers = doc.getElementsByClass("offer_panel");
        List<Pet> pagePets = new ArrayList<>();
        for (Element offer :offers){
            String url = offer.getElementsByTag("h2").first().getElementsByTag("a").first().attr("href");
            String name = offer.getElementsByTag("h2").first().getElementsByTag("a").first().text();
            String description = offer.getElementsByClass("descr").first().text();
            String priceStr = "0";
            for (Element el : offer.getElementsByTag("i") ){
                if (el.text().equals("Цена:")){
                    priceStr = el.nextElementSibling().text();
                }
            }
            Element dt = offer.getElementsByClass("info_c").first();
            String text = dt.getElementsByClass("icon-calendar").first().nextSibling().toString();
            String tStr = text.split("&nbsp;&nbsp")[0];
            Date date = null;
            try{
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                date =  df.parse(tStr);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            log.debug(url+", "+name+", " + date + ", "+priceStr);

            Pet newPet = new Pet();
            newPet.setName(name);
            newPet.setUrl(url);
            newPet.setDescription(description);
            newPet.setPrice(priceStr);
            newPet.setDate(date);
            newPet.setEnable(true);
            pagePets.add(newPet);

        }
        return pagePets;
    }





    private int getPagesCountInCategory(String categoryPath) throws IOException{
        Document  doc = Jsoup.connect(categoryPath)
                .userAgent(USER_AGENT)
                .get();
        Element paginator = doc.getElementsByClass("paginator").first();
        if (paginator == null){
            return 0;
        }
        Element li = paginator.getElementsByClass("next_li").first().previousElementSibling();
        String maxPage = li.getElementsByTag("a").text();
        return Integer.parseInt(maxPage);
    }
    


    private List<Category>  parseMainForCategories (String path) throws IOException {
        List<Category> categories = new ArrayList<>();
        Document  doc = Jsoup.connect(path)
                .userAgent(USER_AGENT)
                .get();
        Elements cats = doc.getElementsByClass("cat");
        for (Element cat : cats) {
            if (cat.parent().parent().children().size() > 1 && cat.previousElementSibling().tag().toString().equals("img") ){
                String sale = cat.parent().parent().child(1).child(0).getElementsByTag("a").text();
                String name = cat.text();
                String url = cat.attr("href");
                log.debug(sale + ", " + name + ", " + url);
                Category newCategory = new Category();
                newCategory.setName(name);
                newCategory.setUrl(url);
                newCategory.setEnable(true);
                if (!url.equals("Домашний-скот/")){
                    //encoding poblem
                    categories.add(newCategory);
                }
            }
        }
        return categories;
    }


}
