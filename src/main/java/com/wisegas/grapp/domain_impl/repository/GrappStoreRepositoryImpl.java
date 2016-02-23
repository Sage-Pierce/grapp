package com.wisegas.grapp.domain_impl.repository;

import com.wisegas.grapp.domain.entity.GrappStore;
import com.wisegas.grapp.domain.repository.GrappStoreRepository;
import com.wisegas.grapp.domain.value.GrappUserID;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class GrappStoreRepositoryImpl extends GenericEntityRepositoryImpl<GrappStore> implements GrappStoreRepository {

   @Override
   public List<GrappStore> findAllForOwner(GrappUserID grappUserID) {
      return entityManager.createQuery(" SELECT grappStore" +
                                       " FROM GrappStore grappStore" +
                                       " WHERE grappStore.owner.id = :grappUserID")
                          .setParameter("grappUserID", grappUserID)
                          .getResultList();
   }
}
