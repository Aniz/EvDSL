//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package {{systemName|lower}}.ev.business;

import java.io.IOException;
import java.util.List;


import com.lowagie.text.DocumentException;

import {{systemName|lower}}.ev.data.CheckingCopy;

import {{systemName|lower}}.ev.exception.CheckingCopyAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.CheckingCopyNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.CheckingCopyRepository;



public class CheckingCopyControl {
	
    private CheckingCopyRepository checkingCopyList;
	
	public CheckingCopyControl(CheckingCopyRepository repository){
		this.checkingCopyList = repository;
	}

	public void insert(CheckingCopy checkingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException{
		if (checkingCopy != null) {
			if (!checkingCopyList.isThere(checkingCopy.getIdCheckingCopy())) 
				checkingCopyList.insert(checkingCopy);
			else
				throw new CheckingCopyAlreadyInsertedException(checkingCopy.getIdCheckingCopy());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idCheckingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		checkingCopyList.remove(idCheckingCopy);
	}
	
	public void update(CheckingCopy checkingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		checkingCopyList.update(checkingCopy);
	}
	
	
	public List<CheckingCopy> getCheckingCopyList() throws RepositoryException {
		return checkingCopyList.getCheckingCopyList();  
	}
	
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingCopyList.getCheckingCopyLastId();
	}

	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyAlreadyInsertedException, RepositoryException, CheckingCopyNotFoundException{
		return checkingCopyList.search(idCheckingCopy);
	}
	
	{% if 'checkingCopyAtestado' in data.statments %}
	// Generated by DSL
	public void emitirAtestado(String nome, String evento, String periodo, CheckingCopy checkingcopy) throws RepositoryException{
		checkingcopy.emitirAtestado(nome, evento, periodo);
	}
	//
	{% endif %}	
	{% if 'checkingCopyCertificado' in data.statments %}
	// Generated by DSL
	public void emitirCertificado(String nome, String evento, String periodo, String atividade, CheckingCopy checkingcopy) throws RepositoryException, DocumentException, IOException{
		checkingcopy.emitirCertificado(nome, evento, periodo, atividade);
	}
	//
	{% endif %}
}
//#endif