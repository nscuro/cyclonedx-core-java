/*
 * This file is part of CycloneDX Core (Java).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * Copyright (c) OWASP Foundation. All Rights Reserved.
 */
package org.cyclonedx.model.formulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.ExtensibleElement;
import org.cyclonedx.model.Property;
import org.cyclonedx.model.Service;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(
    {"bom-ref", "components", "services", "workflows", "properties"})
public class Formula
    extends ExtensibleElement
{

    @JacksonXmlProperty(isAttribute = true, localName = "bom-ref")
    @JsonProperty("bom-ref")
    private String bomRef;

    private List<Component> components;

    private List<Service> services;

    private List<Workflow> workflows;

    private List<Property> properties;

    @JacksonXmlElementWrapper(localName = "components")
    @JacksonXmlProperty(localName = "component")
    public List<Component> getComponents() {
        return components;
    }

    @JacksonXmlElementWrapper(localName = "services")
    @JacksonXmlProperty(localName = "service")
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        if (services == null) {
            services = new ArrayList<>();
        }
        services.add(service);
    }

    @JacksonXmlElementWrapper(localName = "properties")
    @JacksonXmlProperty(localName = "property")
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(property);
    }

    public String getBomRef() {
        return bomRef;
    }

    public void setBomRef(final String bomRef) {
        this.bomRef = bomRef;
    }

    public void setComponents(final List<Component> components) {
        this.components = components;
    }

    @JacksonXmlElementWrapper(localName = "workflows")
    @JacksonXmlProperty(localName = "workflow")
    public List<Workflow> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(final List<Workflow> workflows) {
        this.workflows = workflows;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Formula)) {
            return false;
        }
        Formula formula = (Formula) object;
        return Objects.equals(bomRef, formula.bomRef) && Objects.equals(components, formula.components) &&
            Objects.equals(services, formula.services) && Objects.equals(workflows, formula.workflows) &&
            Objects.equals(properties, formula.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bomRef, components, services, workflows, properties);
    }
}
