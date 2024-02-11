## Generate Key and Self-Signed Certificate
1. Open a terminal/command-prompt window.

2. Move into the directory of your Spring Boot ecommerce project.
    ```
    cd spring-boot-ecommerce
    ```

3. In the terminal window, run this command to generate the key and certificate. This is one long command, copy/paste in its entirety.
    ```
    keytool -genkeypair -alias simtrackusa -keystore src/main/resources/simtrackusa-keystore.p12 -keypass secret -storeType PKCS12 -storepass secret -keyalg RSA -keysize 2048 -validity 365 -dname "C=US, ST=Florida, L=Newberry, O=simtrackusa, OU=Training Backend, CN=localhost" -ext "SAN=dns:localhost"
    ```

| Argument    | Description                        |
|-------------|------------------------------------|
| -genkeypair | Generates a key pair               |
| -alias      | Alias name of the entry to process |
| -keystore   | Name of output keystore file       |
| -keypass    | Key password                       |
| -storeType  | Keystore type                      |
| -storepass  | Keystore password                  |
| -keyalg     | Key algorithm name                 |
| -keysize    | Key bit size                       |
| -validity   | Validity number of days            |
| -dname      | Distinguished name                 |
| -ext        | Add the given X.509 extension      |

> Detailed docs available [here](https://docs.oracle.com/en/java/javase/13/docs/specs/man/keytool.html).

## Verify result
1. View the contents of your certificate.
    ```
    keytool -list -v -alias simtrackusa -keystore src/main/resources/simtrackusa-keystore.p12 -storepass secret
    ```

## Spring Boot HTTPS configs

1. Edit your `application.properties` file
2. Add this snippet for Spring Boot SSL configs to the end of your `application.properties` file

    ```
    #####
    #
    # HTTPS configuration
    #
    #####

    # Server web port
    server.port=8443

    # Enable HTTPS support (only accept HTTPS requests)
    server.ssl.enabled=true

    # Alias that identifies the key in the key store
    server.ssl.key-alias=simtrackusa

    # Keystore location
    server.ssl.key-store=classpath:simtrackusa-keystore.p12

    # Keystore password
    server.ssl.key-store-password=secret

    # Keystore format
    server.ssl.key-store-type=PKCS12
    ```