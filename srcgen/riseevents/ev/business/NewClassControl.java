
package riseevents.ev.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import riseevents.ev.util.LibraryOfDSL;
import riseevents.ev.data.NewClass;
import riseevents.ev.exception.NewClassAlreadyInsertedException;
import riseevents.ev.exception.NewClassNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.NewClassRepository;

public class NewClassControl {
  
  private NewClassRepository newclasss;
  
  public NewClassControl(NewClassRepository repository){
    this.newclasss = repository;
  }
  public void insert(NewClass NewClass) throws NewClassAlreadyInsertedException, RepositoryException{
    if (NewClass != null) {
            if (!newclasss.isThere(NewClass.getIdNewClass())) {
                newclasss.insert(NewClass);
            } else {
                throw new NewClassAlreadyInsertedException(NewClass.getIdNewClass());
            }
        } else {
            throw new IllegalArgumentException();
        }
  }

  
  public boolean isThere(int idNewClass) throws RepositoryException {
    return newclasss.isThere(idNewClass);
  }
  
 }