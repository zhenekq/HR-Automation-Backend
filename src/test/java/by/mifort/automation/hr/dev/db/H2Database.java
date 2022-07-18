package by.mifort.automation.hr.dev.db;

import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class H2Database {
    private static final H2Database instance = new H2Database();
    private static final Integer VALUES_SOURCE_CONSTANT = 1203;

    private H2Database(){}

    public static H2Database getInstance() {
        return instance;
    }

    public List<Candidate> initializeCandidates(){
        return List.of(
                new Candidate("yauheni_vozny", new Timestamp(1L), "status"),
                new Candidate("artem_skrebets", new Timestamp(1L), "status"),
                new Candidate("stas_gutsko", new Timestamp(1L), "status"),
                new Candidate("uliana_fomina", new Timestamp(1L), "status"),
                new Candidate("vladimir_zelmanchuk", new Timestamp(1L), "status"),
                new Candidate("empty_candidate", new Timestamp(1L), "status"),
                new Candidate("yauheni_vozny_clone", new Timestamp(1L), "status")
        );
    }

    public List<CommunicationHistory> initializeHistories(){
        return List.of(
                new CommunicationHistory(1, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment1", Boolean.FALSE, new Candidate("yauheni_vozny")),
                new CommunicationHistory(2, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment2", Boolean.FALSE, new Candidate("artem_skrebets")),
                new CommunicationHistory(3, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment3", Boolean.FALSE, new Candidate("stas_gutsko")),
                new CommunicationHistory(4, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment4", Boolean.FALSE, new Candidate("uliana_fomina")),
                new CommunicationHistory(5, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment5", Boolean.FALSE, new Candidate("vladimir_zelmanchuk")),
                new CommunicationHistory(6, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment6", Boolean.TRUE, new Candidate("yauheni_vozny")),
                new CommunicationHistory(7, new Timestamp(RandomUtils.nextLong()),new Timestamp(RandomUtils.nextLong()), "comment7", Boolean.TRUE, new Candidate("yauheni_vozny"))
        );
    }

    public List<AttributeTypes> initializeAttributeTypes(){
        return List.of(
                new AttributeTypes(1, "phone","string", RandomString.make(), true),
                new AttributeTypes(2, "twitter","string", RandomString.make(), true),
                new AttributeTypes(3, "linkedin","string", RandomString.make(), true),
                new AttributeTypes(4, "githubid","string", RandomString.make(), true),
                new AttributeTypes(5, "company","string", RandomString.make(), false),
                new AttributeTypes(6, "date_of_birth","date", RandomString.make(), false),
                new AttributeTypes(7, "firstname","string", RandomString.make(), false),
                new AttributeTypes(8, "lastname","string", RandomString.make(), false),
                new AttributeTypes(9, "email","string", RandomString.make(), true),
                new AttributeTypes(10, "facebookid","string", RandomString.make(), false),
                new AttributeTypes(11, "passport_number","string", RandomString.make(), false),
                new AttributeTypes(12, "city","string", RandomString.make(), false),
                new AttributeTypes(13, "cityzenship","string", RandomString.make(), false),
                new AttributeTypes(14, "gender","boolean", RandomString.make(), false),
                new AttributeTypes(15, "telegram","string", RandomString.make(), true)
        );
    }

    public List<CandidateAttributes> initializeCandidateAttributes(){
        List<CandidateAttributes> allAttributes = new ArrayList<>();
        List<CandidateAttributes> yauheni_vozny = new ArrayList<>(List.of(
                new CandidateAttributes(1,  "+375295141627", VALUES_SOURCE_CONSTANT, Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(1)),
                new CandidateAttributes(2,  "https://twitter.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(2)),
                new CandidateAttributes(3,  "https://linkedin.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(3)),
                new CandidateAttributes(4,  "https://github.com/zhenekq", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(4)),
                new CandidateAttributes(5,  "Mifort", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(5)),
                new CandidateAttributes(6,  "09.01.2002",VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(6)),
                new CandidateAttributes(7,  "Yauheni",VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(7)),
                new CandidateAttributes(8,  "Vozny", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(8)),
                new CandidateAttributes(9,  "zhenek02ss@gmail.com", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(9)),
                new CandidateAttributes(10,  "https://facebook.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(10)),
                new CandidateAttributes(11,  "KH9083221",VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(11)),
                new CandidateAttributes(12,  "Minsk", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(12)),
                new CandidateAttributes(13,  "Belarussian", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(13)),
                new CandidateAttributes(14,  "1", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(14)),
                new CandidateAttributes(15,  "https://t.me/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny"), new AttributeTypes(15))
        ));
        List<CandidateAttributes> uliana_fomina = new ArrayList<>(List.of(
                new CandidateAttributes(16,  "+375291243781",VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(1)),
                new CandidateAttributes(17,  "https://twitter.com/fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(2)),
                new CandidateAttributes(18,  "https://linkedin.com/fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(3)),
                new CandidateAttributes(19,  "https://github.com/fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(4)),
                new CandidateAttributes(20,  "Mifort", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(5)),
                new CandidateAttributes(21,  "17.02.2003", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(6)),
                new CandidateAttributes(22,  "Uliana", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(7)),
                new CandidateAttributes(23,  "Fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(8)),
                new CandidateAttributes(24,  "fomina@gmail.com", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(9)),
                new CandidateAttributes(25,  "https://facebook.com/fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(10)),
                new CandidateAttributes(26,  "KH8723637", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(11)),
                new CandidateAttributes(27,  "Minsk", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(12)),
                new CandidateAttributes(28,  "Belarussian", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(13)),
                new CandidateAttributes(29,  "0", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(14)),
                new CandidateAttributes(30,  "https://t.me/fomina", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("uliana_fomina"), new AttributeTypes(15))
        ));
        List<CandidateAttributes> artem_skrebets = new ArrayList<>(List.of(
                new CandidateAttributes(31,  "+375291243783", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(1)),
                new CandidateAttributes(32,  "https://twitter.com/artem_skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(2)),
                new CandidateAttributes(33,  "https://linkedin.com/artem_skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(3)),
                new CandidateAttributes(34,  "https://github.com/artem_skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(4)),
                new CandidateAttributes(35,  "Mifort", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(5)),
                new CandidateAttributes(36,  "17.02.2003", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(6)),
                new CandidateAttributes(37,  "Artem", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(7)),
                new CandidateAttributes(38,  "Skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(8)),
                new CandidateAttributes(39,  "artem_skrebets@gmail.com", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(9)),
                new CandidateAttributes(40,  "https://facebook.com/artem_skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(10)),
                new CandidateAttributes(41,  "KH1234123", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(11)),
                new CandidateAttributes(42,  "Minsk",VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(12)),
                new CandidateAttributes(43,  "Belarussian", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(13)),
                new CandidateAttributes(44,  "1", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(14)),
                new CandidateAttributes(45,  "https://t.me/artem_skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("artem_skrebets"), new AttributeTypes(15))
        ));
        List<CandidateAttributes> yauheni_vozny_clone = new ArrayList<>(List.of(
                new CandidateAttributes(46,  "+375295141627", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(1)),
                new CandidateAttributes(47,  "https://twitter.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(2)),
                new CandidateAttributes(48,  "https://linkedin.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(3)),
                new CandidateAttributes(49,  "https://github.com/zhenekq", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(4)),
                new CandidateAttributes(50,  "Mifort", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(5)),
                new CandidateAttributes(51,  "09.01.2002", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(6)),
                new CandidateAttributes(52,  "Yauheni", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(7)),
                new CandidateAttributes(53,  "Vozny", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(8)),
                new CandidateAttributes(54,  "zhenek02ss@gmail.com", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(9)),
                new CandidateAttributes(55,  "https://facebook.com/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(10)),
                new CandidateAttributes(56,  "KH9083221", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(11)),
                new CandidateAttributes(57,  "Minsk", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(12)),
                new CandidateAttributes(58,  "Belarussian", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(13)),
                new CandidateAttributes(59,  "1", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(14)),
                new CandidateAttributes(60,  "https://t.me/zhenekns", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("yauheni_vozny_clone"), new AttributeTypes(15))
        ));

        List<CandidateAttributes> vladimir_zelmanchuk = new ArrayList<>(List.of(
                new CandidateAttributes(61,  "+37529387432", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(1)),
                new CandidateAttributes(62,  "https://twitter.com/iudfuhsdfoij", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(2)),
                new CandidateAttributes(63,  "https://linkedin.com/iusd92jnd", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(3)),
                new CandidateAttributes(64,  "https://github.com/ijdiusjdiudee", VALUES_SOURCE_CONSTANT, Boolean.FALSE,new Candidate("vladimir_zelmanchuk"), new AttributeTypes(4)),
                new CandidateAttributes(65,  "Mifort", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(5)),
                new CandidateAttributes(66,  "17.02.2003", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(6)),
                new CandidateAttributes(67,  "Artem", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(7)),
                new CandidateAttributes(68,  "Skrebets", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(8)),
                new CandidateAttributes(69,  "iushfuish@gmail.com", VALUES_SOURCE_CONSTANT, Boolean.FALSE,new Candidate("vladimir_zelmanchuk"), new AttributeTypes(9)),
                new CandidateAttributes(70,  "https://facebook.com/iusdhfuis", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(10)),
                new CandidateAttributes(71,  "RK8992823", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(11)),
                new CandidateAttributes(72,  "Minsk", VALUES_SOURCE_CONSTANT, Boolean.FALSE,new Candidate("vladimir_zelmanchuk"), new AttributeTypes(12)),
                new CandidateAttributes(73,  "Belarussian", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(13)),
                new CandidateAttributes(74,  "1", VALUES_SOURCE_CONSTANT,Boolean.FALSE, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(14)),
                new CandidateAttributes(75,  "https://t.me/876234yuhfjd", VALUES_SOURCE_CONSTANT, new Candidate("vladimir_zelmanchuk"), new AttributeTypes(15))
        ));

        allAttributes.addAll(yauheni_vozny);
        allAttributes.addAll(uliana_fomina);
        allAttributes.addAll(artem_skrebets);
        allAttributes.addAll(yauheni_vozny_clone);
        allAttributes.addAll(vladimir_zelmanchuk);
        return allAttributes;
    }



    public AttributeTypes getAttributeTypeWithRandomValues(){
         return new AttributeTypes(RandomString.make(),
                                   RandomString.make(),
                                   RandomString.make(),
                                   RandomUtils.nextBoolean());
    }

    public CandidateAttributes getCandidateAttributesWithRandomValues(String candidateId, Integer type){
        CandidateAttributes attributes = new CandidateAttributes(RandomString.make(), RandomUtils.nextInt());
        attributes.setCandidate(new Candidate(candidateId));
        attributes.setAttributeTypes(new AttributeTypes(type));

        return attributes;
    }

    public Candidate getCandidateWithRandomValues(){
        return new Candidate(RandomString.make(),
                              new Timestamp(RandomUtils.nextLong()),
                              RandomString.make());
    }
}
