//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.data;

import java.sql.Blob;

public class Review {
	
	public enum StatusReview{
	{% for category in data.option.categories %}
		//generated By DSL
		{% if loop.last %}
			{{category.name}}
		{% else %}
			{{category.name}},
		{% endif %}
		//
	{% endfor %}
	}
	
	private int idReview;
	private int idSubmission;
	private StatusReview status;
	private String date;
	private String description;
	private Blob attachment;
	private int round;
	private String result;
{% for property in data.properties %}
		//generated By DSL
	private {{property.type|javatype}} {{property.name}};
{% endfor %}

	public int getIdReview() {
		return idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}
	public int getIdSubmission() {
		return idSubmission;
	}
	public void setIdSubmission(int idSubmission) {
		this.idSubmission = idSubmission;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusReview getStatus() {
		return status;
	}
	public void setStatus(StatusReview status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	{% for property in data.properties %}
  	//generated By DSL
  	public {{property.type|javatype}} get{{property.name|capitalize}}(){
    	return this.{{property.name}};
  	}
	public void set{{property.name|capitalize}}({{property.type|javatype}} new_value){
    	this.{{property.name}} = new_value;
  	}
  	{% endfor %}
	
	public String toString(){
		return "Review Id:"+ idReview + "\nSubmission Id:" + idSubmission + "\nStatus:" + status.toString() + "\nDate:" + date + "\nDescription:" + description + "\nRound:" + round + "\nResult:" + result;
	}
	
}
//#endif