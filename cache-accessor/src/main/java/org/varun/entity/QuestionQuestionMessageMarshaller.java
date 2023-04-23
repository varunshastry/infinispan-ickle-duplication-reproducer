package org.varun.entity;

import org.infinispan.protostream.MessageMarshaller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class QuestionQuestionMessageMarshaller implements MessageMarshaller<QuestionQuestion> {

	@Override
	public Class<QuestionQuestion> getJavaClass() {
		return QuestionQuestion.class;
	}

	@Override
	public String getTypeName() {
		return "life.genny.qwandaq.persistence.questionquestion.QuestionQuestion";
	}

	// @Override
	public QuestionQuestion readFrom(ProtoStreamReader reader) throws IOException {
		QuestionQuestion questionQuestion = new QuestionQuestion();
		questionQuestion.setSourceCode(reader.readString("sourceCode"));
		questionQuestion.setTargetCode(reader.readString("targetCode"));
		Long createdLong = reader.readLong("created");
		if (createdLong != null) {
			questionQuestion.setCreated(LocalDateTime.ofEpochSecond(createdLong / 1000, 0, ZoneOffset.UTC));
		}
		questionQuestion.setDisabled(reader.readBoolean("disabled"));
		questionQuestion.setHidden(reader.readBoolean("hidden"));
		questionQuestion.setIcon(reader.readString("icon"));
		questionQuestion.setMandatory(reader.readBoolean("mandatory"));
		questionQuestion.setReadonly(reader.readBoolean("readonly"));
		questionQuestion.setRealm(reader.readString("realm"));
		Long updatedLong = reader.readLong("updated");
		if (updatedLong != null) {
			questionQuestion.setUpdated(LocalDateTime.ofEpochSecond(updatedLong / 1000, 0, ZoneOffset.UTC));
		}
		questionQuestion.setVersion(reader.readLong("version"));
		questionQuestion.setWeight(reader.readDouble("weight"));
		questionQuestion.setCapreqs(reader.readString("capreqs"));
		return questionQuestion;
	}

	// @Override
	public void writeTo(ProtoStreamWriter writer, QuestionQuestion questionQuestion) throws IOException {
		writer.writeString("sourceCode", questionQuestion.getSourceCode());
		writer.writeString("targetCode", questionQuestion.getTargetCode());
		LocalDateTime created = questionQuestion.getCreated();
		Long createdLong = created != null ? created.toEpochSecond(ZoneOffset.UTC)*1000 : null;
		writer.writeLong("created", createdLong);
		writer.writeBoolean("disabled", questionQuestion.getDisabled());
		writer.writeBoolean("hidden", questionQuestion.getHidden());
		writer.writeString("icon", questionQuestion.getIcon());
		writer.writeBoolean("mandatory", questionQuestion.getMandatory());
		writer.writeBoolean("readonly", questionQuestion.getReadonly());
		writer.writeString("realm", questionQuestion.getRealm());
		LocalDateTime updated = questionQuestion.getUpdated();
		Long updatedLong = created != null ? updated.toEpochSecond(ZoneOffset.UTC)*1000 : null;
		writer.writeLong("updated", updatedLong);
		writer.writeLong("version", questionQuestion.getVersion());
		writer.writeDouble("weight", questionQuestion.getWeight());
		writer.writeString("capreqs", questionQuestion.getCapreqs());
	}
}
