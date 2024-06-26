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
package org.cyclonedx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.cyclonedx.Version;
import org.cyclonedx.util.deserializer.LicenseDeserializer;
import org.cyclonedx.util.deserializer.LifecycleDeserializer;
import org.cyclonedx.util.deserializer.MetadataDeserializer;
import org.cyclonedx.util.serializer.CustomDateSerializer;
import org.cyclonedx.model.metadata.ToolInformation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "timestamp", "lifecycles", "tools", "authors", "component", "manufacturer", "manufacture", "supplier", "licenses",
    "properties"
})
@JsonDeserialize(using = MetadataDeserializer.class)
public class Metadata
    extends ExtensibleElement
{

    @JsonSerialize(using = CustomDateSerializer.class)
    @VersionFilter(Version.VERSION_12)
    private Date timestamp = new Date();

    @VersionFilter(Version.VERSION_15)
    @JsonProperty("lifecycles")
    @JsonDeserialize(using = LifecycleDeserializer.class)
    @JacksonXmlElementWrapper(localName = "lifecycles")
    @JacksonXmlProperty(localName = "lifecycle")
    private Lifecycles lifecycles;

    @VersionFilter(Version.VERSION_12)
    @Deprecated
    private List<Tool> tools;

    @JacksonXmlElementWrapper(localName = "tools")
    @JacksonXmlProperty(localName = "tool")
    @VersionFilter(Version.VERSION_15)
    private ToolInformation toolInformation;

    @VersionFilter(Version.VERSION_12)
    private List<OrganizationalContact> authors;

    @VersionFilter(Version.VERSION_12)
    private Component component;

    @VersionFilter(Version.VERSION_16)
    @JsonProperty("manufacturer")
    private OrganizationalEntity manufacturer;

    @Deprecated
    @VersionFilter(Version.VERSION_12)
    private OrganizationalEntity manufacture;

    @VersionFilter(Version.VERSION_12)
    private OrganizationalEntity supplier;

    @VersionFilter(Version.VERSION_13)
    private LicenseChoice licenses;

    @VersionFilter(Version.VERSION_13)
    private List<Property> properties;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @JacksonXmlElementWrapper(localName = "tools")
    @JacksonXmlProperty(localName = "tool")
    @JsonIgnore
    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public void addTool(Tool tool) {
        if (this.tools == null) {
            this.tools = new ArrayList<>();
        }
        this.tools.add(tool);
    }

    @JacksonXmlElementWrapper(localName = "authors")
    @JacksonXmlProperty(localName = "author")
    public List<OrganizationalContact> getAuthors() {
        return authors;
    }

    public void setAuthors(List<OrganizationalContact> authors) {
        this.authors = authors;
    }

    public void addAuthor(OrganizationalContact author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Deprecated
    public OrganizationalEntity getManufacture() {
        return manufacture;
    }

    @Deprecated
    public void setManufacture(OrganizationalEntity manufacture) {
        this.manufacture = manufacture;
    }

    public OrganizationalEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(OrganizationalEntity supplier) {
        this.supplier = supplier;
    }

    @Deprecated
    public LicenseChoice getLicenseChoice() {
        return getLicenses();
    }

    @Deprecated
    @JsonIgnore
    public void setLicenseChoice(LicenseChoice licenseChoice) {
        setLicenses(licenseChoice);
    }

    @JsonDeserialize(using = LicenseDeserializer.class)
    public LicenseChoice getLicenses() {
        return licenses;
    }

    @JacksonXmlElementWrapper (useWrapping = false)
    public void setLicenses(LicenseChoice licenses) {
        this.licenses = licenses;
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

    public Lifecycles getLifecycles() {
        return lifecycles;
    }

    public void setLifecycles(final Lifecycles lifecycles) {
        this.lifecycles = lifecycles;
    }

    @JacksonXmlElementWrapper(localName = "tools")
    @JacksonXmlProperty(localName = "tool")
    public ToolInformation getToolChoice() {
        return toolInformation;
    }

    public void setToolChoice(final ToolInformation toolInformation) {
        this.toolInformation = toolInformation;
    }

    public OrganizationalEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final OrganizationalEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(timestamp, metadata.timestamp) &&
                Objects.equals(authors, metadata.authors) &&
                Objects.equals(component, metadata.component) &&
                Objects.equals(manufacture, metadata.manufacture) &&
                Objects.equals(supplier, metadata.supplier) &&
                Objects.equals(licenses, metadata.licenses) &&
                Objects.equals(lifecycles, metadata.lifecycles) &&
                Objects.equals(toolInformation, metadata.toolInformation) &&
                Objects.equals(properties, metadata.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, toolInformation, authors, component, manufacture, supplier, licenses, properties,
            lifecycles);
    }
}
