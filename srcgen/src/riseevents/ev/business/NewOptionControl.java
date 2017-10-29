
package riseevents.ev.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import riseevents.ev.util.LibraryOfDSL;
import riseevents.ev.data.NewOption;
import riseevents.ev.exception.NewOptionAlreadyInsertedException;
import riseevents.ev.exception.NewOptionNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.NewOptionRepository;

public class NewOptionControl {
  
  private NewOptionRepository newoptionList;
  
  public NewOptionControl(NewOptionRepository repository){
    this.newoptionList = repository;
  }
  public void insert(NewOption newoption) throws NewOptionAlreadyInsertedException, RepositoryException{
    if (newoption != null) {
            if (!newoptionList.isThere(newoption.getIdNewOption())) {
                newoptionList.insert(newoption);
            } else {
                throw new NewOptionAlreadyInsertedException(newoption.getIdNewOption());
            }
        } else {
            throw new IllegalArgumentException();
        }
  }

  public void remove(int idNewOption) throws NewOptionAlreadyInsertedException, RepositoryException, NewOptionNotFoundException{
    newoptionList.remove(idNewOption);
  }
  public void update(NewOption NewOption) throws NewOptionAlreadyInsertedException, RepositoryException, NewOptionNotFoundException{
  newoptionList.update(NewOption);
  }
  public NewOption search(int idNewOption) throws NewOptionAlreadyInsertedException, RepositoryException, NewOptionNotFoundException{
    return newoptionList.search(idNewOption);
  }
  
  public boolean isThere(int idNewOption) throws RepositoryException {
    return newoptionList.isThere(idNewOption);
  }

  public int getNewOptionLastId() throws RepositoryException{
    return newoptionList.getNewOptionLastId();
  }
  
  public List<NewOption> getNewOptionList() throws RepositoryException {
    return newoptionList.getNewOptionList();  
  } 
 }