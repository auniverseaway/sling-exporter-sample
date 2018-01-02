# About
This project shows how Sling Model Exporters do not set character encoding and creates a problem when attempting to view localized content.

# Requirements
* Sling 9
* Maven 3
* Java 8

# Install
Once Sling 9 is running on localhost:8080...

    mvn clean install -PautoInstallBundle
    
# Observe

1. http://localhost:8080/content/millr/sample-jp.0.json - Default GET Servlet works great.
2. http://localhost:8080/content/millr/sample-jp.servlet.json - Custom Servlet + Model + Jackson works great.
3. http://localhost:8080/content/millr/sample-jp.model.json - Sling Model Exporter does not.

Number three is Sling Model Exporter OOTB behavior where character encoding is not set, so it defaults to `ISO-8859-1` which causes issues with many pieces of localized content.

Screenshots with specific highlights callingout the issue can be found here: https://github.com/auniverseaway/sling-exporter-sample/tree/master/readme-assets

# Expectations
The Jackson / JSON implementation of the Sling Model exporter should correctly output `UTF-8` as it is the standard character encoding for `application/json` content types.
