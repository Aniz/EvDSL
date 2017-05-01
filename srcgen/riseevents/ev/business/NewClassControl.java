
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
  
  private NewClassRepository newclassList;
  
  public NewClassControl(NewClassRepository repository){
    this.newclassList = repository;
  }
  public void insert(NewClass newclass) throws NewClassAlreadyInsertedException, RepositoryException{
    if (newclass != null) {
            if (!newclassList.isThere(newclass.getIdNewClass())) {
                newclassList.insert(newclass);
            } else {
                throw new NewClassAlreadyInsertedException(newclass.getIdNewClass());
            }
        } else {
            throw new IllegalArgumentException();
        }
  }

  
  public boolean isThere(int idNewClass) throws RepositoryException {
    return newclassList.isThere(idNewClass);
  }
  
 }