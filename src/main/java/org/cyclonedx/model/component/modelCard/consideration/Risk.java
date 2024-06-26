package org.cyclonedx.model.component.modelCard.consideration;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cyclonedx.util.deserializer.RiskDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(using = RiskDeserializer.class)
public class Risk
{
  private String name;

  private String mitigationStrategy;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getMitigationStrategy() {
    return mitigationStrategy;
  }

  public void setMitigationStrategy(final String mitigationStrategy) {
    this.mitigationStrategy = mitigationStrategy;
  }

  @Override
  public boolean equals(final Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Risk)) {
      return false;
    }
    Risk risk = (Risk) object;
    return Objects.equals(name, risk.name) &&
        Objects.equals(mitigationStrategy, risk.mitigationStrategy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, mitigationStrategy);
  }
}
