package org.varun.entity;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * A representation of QuestionQuestion in the cache
 * 
 * @author Varun Shastry
 */
@XmlRootElement
public class QuestionQuestion implements Serializable {

	@ProtoField(1)
	private String sourceCode;
	@ProtoField(2)
	private String targetCode;

	@ProtoField(3)
	private boolean createOnTrigger;

	@ProtoField(4)
	private LocalDateTime created;

	@ProtoField(5)
	private String dependency;

	@ProtoField(6)
	private Boolean disabled;

	@ProtoField(7)
	private Boolean formTrigger;

	@ProtoField(8)
	private Boolean hidden;

	@ProtoField(9)
	private String icon;

	@ProtoField(10)
	private Boolean mandatory;

	@ProtoField(11)
	private Boolean onsehot;

	@ProtoField(12)
	private Boolean readonly;

	@ProtoField(13)
	private String realm;

	@ProtoField(14)
	private LocalDateTime updated;

	@ProtoField(15)
	private Long version = 1L;

	@ProtoField(16)
	private Double weight;

	@ProtoField(17)
	private String capreqs;

	@ProtoFactory
	public QuestionQuestion() {
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public boolean isCreateOnTrigger() {
		return createOnTrigger;
	}

	public void setCreateOnTrigger(boolean createOnTrigger) {
		this.createOnTrigger = createOnTrigger;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getFormTrigger() {
		return formTrigger;
	}

	public void setFormTrigger(Boolean formTrigger) {
		this.formTrigger = formTrigger;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Boolean getOnsehot() {
		return onsehot;
	}

	public void setOnsehot(Boolean onsehot) {
		this.onsehot = onsehot;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCapreqs() {
		return capreqs;
	}

	public void setCapreqs(String capreqs) {
		this.capreqs = capreqs;
	}

}
