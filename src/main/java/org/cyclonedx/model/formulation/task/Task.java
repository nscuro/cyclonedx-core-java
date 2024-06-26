package org.cyclonedx.model.formulation.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.cyclonedx.model.formulation.FormulationCommon;


@JacksonXmlRootElement(localName = "task")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(
    {
        "bom-ref", "uid", "name", "description", "resourceReferences", "taskTypes", "trigger", "steps", "inputs",
        "outputs", "timeStart", "timeEnd", "workspaces", "runtimeTopology", "taskDependencyGraph", "properties"
    })
public class Task
    extends FormulationCommon
{

}
