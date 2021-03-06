package net.lezhang.spring.beanbasic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import net.lezhang.spring.data.Offer;
import net.lezhang.spring.data.OfferDAO;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ApplicationContext context = new FileSystemXmlApplicationContext("myBeans.xml");
        OfferDAO offerDao = (OfferDAO)context.getBean("offerDao");
        try {
            System.out.println("Reading database: ");
            List<Offer> offers = offerDao.getOffers();
            for(Offer offer : offers) {
                System.out.println(offer);
            }
            
            System.out.println("Testing named parameters: ");
            List<Offer> namedOffers = offerDao.getOffers("Bond");
            for(Offer offer : namedOffers) {
                System.out.println(offer);
            }
            
            System.out.println("Testing getting single object from database: ");
            System.out.println(offerDao.getOffer(2));
            
            System.out.println("Testing deleting from database: ");
            System.out.println(offerDao.deleteOffer(2));

            System.out.println("Trying to get the single object from database again should throw an error now: ");
            System.out.println(offerDao.getOffer(2));

            System.out.println("Adding the poor guy back: ");
            System.out.println(offerDao.insertOffer(2));

            System.out.println("Trying to get the single object from database again should work now: ");
            System.out.println(offerDao.getOffer(2));

            System.out.println("Adding via Object: ");
            Offer offer1 = new Offer("Dave", "dave@email.com", "offer from Dave");
            Offer offer2 = new Offer("Karen", "karen@email.com", "offer from Karen");
            System.out.println(offerDao.insertOfferUsingObject(offer1));
            System.out.println(offerDao.insertOfferUsingObject(offer2));
            offers = offerDao.getOffers();
            for(Offer offer : offers) {
                System.out.println(offer);
            }

            System.out.println("Updating via Object:");
            Offer offer3 = new Offer(7, "New Name", "update@email.com", "updated offer");
            System.out.println(offerDao.updateOffer(offer3));
            offers = offerDao.getOffers();
            for(Offer offer : offers) {
                System.out.println(offer);
            }

            System.out.println("Adding in batch:");
            List<Offer> offersToAdd = new ArrayList<>();
            offersToAdd.add(offer1);
            offersToAdd.add(offer2);
            offersToAdd.add(offer3);
            System.out.println(offerDao.insertOffers(offersToAdd));
            offers = offerDao.getOffers();
            for(Offer offer : offers) {
                System.out.println(offer);
            }

            System.out.println("Testing transaction:");
            try {
                System.out.println("result: " + offerDao.transactionalInsert());
                // will not reach as exception is thrown
            } catch(Exception e) {
                System.out.println(e);
            }
            offers = offerDao.getOffers();
            for(Offer offer : offers) {
                System.out.println(offer);
            }
            // should be no diff, indicating no change in DB

            System.out.println("---------- end of DB testing ----------");

        } catch(DataAccessException ex) {
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
        }
        
        Person person = (Person)context.getBean("person");
        person.speak();
        Person person1 = (Person)context.getBean("person1");
        person1.speak();
        
        // singleton as default scope
        Person person2 = (Person)context.getBean("person1");
        person2.speak();
        System.out.println("person2 == person1: " + (person2 == person1));
        
        // using properties file
        person2 = (Person)context.getBean("person2");
        person2.speak();

        ((FileSystemXmlApplicationContext)context).close();
        
        // other ways to find bean config xml
        ApplicationContext context1 = new FileSystemXmlApplicationContext("src/main/java/net/lezhang/spring/beanbasic/config/myBeans1.xml");
        person = (Person)context1.getBean("person");
        person.speak();
        ((FileSystemXmlApplicationContext)context1).close();

        ApplicationContext context2 = new ClassPathXmlApplicationContext("net/lezhang/spring/beanbasic/config/myBeans1.xml");
        person = (Person)context2.getBean("person");
        person.speak();
        ((ClassPathXmlApplicationContext)context2).close();

        System.out.println( "Shutting down..." );
    }
}
