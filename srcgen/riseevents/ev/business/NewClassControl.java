
package riseevents.ev.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import riseevents.ev.util.LibraryOfDSL;
import riseevents.ev.data.Newclass;
import riseevents.ev.exception.NewclassAlreadyInsertedException;
import riseevents.ev.exception.NewclassNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.NewclassRepository;

public class NewclassControl {
  
  private NewclassRepository newclassList;
  
  public NewclassControl(NewclassRepository repository){
    this.newclassList = repository;
  }
  public void insert(Newclass newclass) throws NewclassAlreadyInsertedException, RepositoryException{
    if (newclass != null) {
            if (!newclass.isThere(newclass.getIdNewclass())) {
                newclass.insert(newclass);
            } else {
                throw new NewclassAlreadyInsertedException(newclass.getIdNewclass());
            }
        } else {
            throw new IllegalArgumentException();
        }
  }

  
  public boolean isThere(int idNewclass) throws RepositoryException {
    return newclassList.isThere(idNewclass);
  }
  
 }