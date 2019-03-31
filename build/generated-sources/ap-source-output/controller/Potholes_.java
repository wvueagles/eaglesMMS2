package controller;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-17T14:39:32")
@StaticMetamodel(Potholes.class)
public class Potholes_ { 

    public static volatile SingularAttribute<Potholes, Integer> severity;
    public static volatile SingularAttribute<Potholes, Date> createdat;
    public static volatile SingularAttribute<Potholes, String> comments;
    public static volatile SingularAttribute<Potholes, String> reportingpersonkey;
    public static volatile SingularAttribute<Potholes, String> location;
    public static volatile SingularAttribute<Potholes, Date> closedat;
    public static volatile SingularAttribute<Potholes, Integer> workid;
    public static volatile SingularAttribute<Potholes, String> status;
    public static volatile SingularAttribute<Potholes, Date> updatedat;

}