package by.mifort.automation.hr.dev.util.differences.impl;

import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.dto.ChangeSet;
import by.mifort.automation.hr.dev.dto.Type;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;

import java.sql.Timestamp;
import java.util.*;

public class    AsserDifferencesCandidateListAttributes {

    private final EntityConverter<CandidateAttributes, CandidateAttributesDto> converter;

    public AsserDifferencesCandidateListAttributes(EntityConverter<CandidateAttributes, CandidateAttributesDto> converter){
        this.converter = converter;
    }

    public static List<CandidateAttributes> assertDiff(List<CandidateAttributes> db, List<CandidateAttributes> body){
        if(db == null){
            return body;
        }
        Set<CandidateAttributes> resultDto = new HashSet<>();
        for(int i=0;i<body.size();i++){
            CandidateAttributes dbDto = body.get(i);
            Integer dbDtoId = dbDto.getAttributeTypes().getId();
            CandidateAttributes dbBody = null;
            boolean isExists = false;
            for(int j=0;j<db.size();j++){
                dbBody = db.get(j);
                Integer dbBodyId = dbBody.getAttributeTypes().getId();
                if(dbBodyId.equals(dbDtoId)){
                    dbBody.setValueSource(dbDto.getValueSource());
                    dbBody.setValue(dbDto.getValue());
                    dbBody.setAttributeTypes(dbDto.getAttributeTypes());
                    resultDto.add(dbBody);
                    isExists = true;
                    break;
                }
            }
            if(!isExists){
                resultDto.add(dbDto);
            }
        }
        return new ArrayList<>(resultDto);
    }

    public static CandidateUpdate getUpdates(List<CandidateAttributes> oldAttr, List<CandidateAttributes> newAttr){
        List<ChangeSet> changeSets = new ArrayList<>();
        CandidateUpdate update = new CandidateUpdate();
        for(int i=0;i<oldAttr.size();i++){
            CandidateAttributes o = oldAttr.get(i);
            for(int j=0;j<newAttr.size();j++){
                CandidateAttributes n = newAttr.get(j);
                if(o.getAttributeTypes().getId().equals(n.getAttributeTypes().getId()) &&
                !o.getValue().equals(n.getValue())){
                    if(o.getValue().equals("") || o.getValue().isEmpty()){
                        continue;
                    }
                    ChangeSet changeSet = new ChangeSet();
                    changeSet.setOldValue(o.getValue());
                    changeSet.setNewValue(n.getValue());
                    changeSet.setType(new Type(o.getAttributeTypes().getId(), o.getAttributeTypes().getName()));
                    changeSets.add(changeSet);
                }
            }
        }
        update.setUpdateDate(new Timestamp(new Date().getTime()));
        update.setChangeSet(changeSets);
        update.setCandidate(new Candidate(oldAttr.get(0).getCandidate().getId()));
        update.setSource(oldAttr.get(0).getValueSource().toString());
        return update;
    }
}
