package lk.ijse.hibernate.util;

import lk.ijse.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static SessionFactory sessionFactory= createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry stg = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();
        Metadata metadata=new MetadataSources(stg)
                .addAnnotatedClass(Student.class).addAnnotatedClass(Parent.class).addAnnotatedClass(Program.class).addAnnotatedClass(StudentProgram.class).addAnnotatedClass(LoginMaster.class)
                .getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getInstance(){
        return sessionFactory;
    }
}
