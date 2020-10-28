# Base Starter for Vaadin Flow and OSGi

## Coordinates

### Maven

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>starter.flow.better</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### OSGi

```
Bundle Symbolic Name: com.example.starter.flow.better
Version             : 1.0.0
```

### Feature-Coordinate

```
"bundles": [
   {
    "id": "com.example:starter.flow.better:1.0-SNAPSHOT"
   }
]
```

## Components

### com.example.starter.flow.better.osgi.FixedVaadinServlet - *state = enabled, activation = delayed*

#### Description

#### Services - *scope = singleton*

|Interface name |
|--- |
|javax.servlet.Servlet |

#### Properties

|Name |Type |Value |
|--- |--- |--- |
|servlet.init.compatibilityMode |Boolean |false |
|osgi.http.whiteboard.servlet.asyncSupported |Boolean |true |
|osgi.http.whiteboard.servlet.pattern |String[] |["/*"] |

#### Configuration - *policy = optional*

##### Pid: `com.example.starter.flow.better.osgi.FixedVaadinServlet`

No information available.

#### Reference bindings

|Attribute |Value |
|--- |--- |
|name |pr |
|interfaceName |com.vaadin.flow.di.ResourceProvider |
|target | |
|cardinality |1..1 |
|policy |static |
|policyOption |reluctant |
|scope |bundle |

#### OSGi-Configurator


```
/*
 * Component: com.example.starter.flow.better.osgi.FixedVaadinServlet
 * policy:    optional
 */
"com.example.starter.flow.better.osgi.FixedVaadinServlet":{
        //# Component properties
        /*
         * Type = Boolean
         * Default = false
         */
         // "servlet.init.compatibilityMode": null,

        /*
         * Type = Boolean
         * Default = true
         */
         // "osgi.http.whiteboard.servlet.asyncSupported": null,

        /*
         * Type = String[]
         * Default = ["/*"]
         */
         // "osgi.http.whiteboard.servlet.pattern": null,


        //# Reference bindings
        // "pr.target": "(component.pid=*)"


        //# ObjectClassDefinition - Attributes
        // (No PidOcd available.)
}
```

---

### com.example.starter.flow.better.osgi.IconResource - *state = enabled, activation = immediate*

#### Description

Registers icon using the OSGi-Http-Whiteboard - @HttpWhiteboardResource

#### Services - *scope = singleton*

|Interface name |
|--- |
|com.example.starter.flow.better.osgi.IconResource |

#### Properties

|Name |Type |Value |
|--- |--- |--- |
|service.description |String |"Registers icon using the OSGi-Http-Whiteboard - @HttpWhiteboardResource" |
|osgi.http.whiteboard.resource.pattern |String[] |["/icons/icon.png"] |
|osgi.http.whiteboard.resource.prefix |String |"/META-INF/resources/icons/icon.png" |

#### Configuration - *policy = optional*

##### Pid: `com.example.starter.flow.better.osgi.IconResource`

No information available.

#### Reference bindings

No bindings.

#### OSGi-Configurator


```
/*
 * Component: com.example.starter.flow.better.osgi.IconResource
 * policy:    optional
 */
"com.example.starter.flow.better.osgi.IconResource":{
        //# Component properties
        /*
         * Type = String
         * Default = "Registers icon using the OSGi-Http-Whiteboard - @HttpWhiteboardResource"
         */
         // "service.description": null,

        /*
         * Type = String[]
         * Default = ["/icons/icon.png"]
         */
         // "osgi.http.whiteboard.resource.pattern": null,

        /*
         * Type = String
         * Default = "/META-INF/resources/icons/icon.png"
         */
         // "osgi.http.whiteboard.resource.prefix": null,


        //# Reference bindings
        // none

        //# ObjectClassDefinition - Attributes
        // (No PidOcd available.)
}
```

## Licenses

**http://www.apache.org/licenses/LICENSE-2.0**