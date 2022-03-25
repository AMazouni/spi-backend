package fr.ubo.spibackend.entities.id;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.entities.Promotion;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

public class CandidatIdGen implements IdentifierGenerator {

    String prefix;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

       String  id="";
       id = Long.valueOf(UUID.randomUUID().getMostSignificantBits()).toString();


        return prefix+id;
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        IdentifierGenerator.super.configure(type, params, serviceRegistry);
        setPrefix(params.getProperty("appendString")); // Here we are setting the parameters.
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
