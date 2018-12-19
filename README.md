# HASHCNPJ

HASHCNPJ is a CA Fast Data Masker custom function, made to enable CA FDM to consistently mask [Brazilian CNPJs](https://en.wikipedia.org/wiki/CNPJ).

## Main Features

* The function will hash Brazilian CNPJ with or without mask.
* The function ensures the same format and consistent masks for the same original value, but does not ensure uniqueness.
* The mask algorithm is based on CA Fast Data Masker FORMATHASH.
* It will maintain nulls and empty strings.

## Getting Started

### Build Prerequisites

These instructions for how to get and compile the source code. The project was created with **Gradle** to build and handle dependencies. All required libs (*minus one, see below*) are handled automatically by Gradle. This project works with **Java 1.8+**.

```
gradle init --type java-library
```

**CA FastDataMasker lib** is also required. It's presented as part of the Fast Data Masker application, which can only by used by those whom has a valid CA FDM license. The lib is not presented at this repository and should be manually added to *libs* folder, after you have push the code into your workstation.

```
Project Root
  --libs
    --FastDataMasker.jar
```

### Installing

A step by step, with examples, that tell you how to get the built function working on a CA Fast Data Masker installation.

First of all, custom functions support were added at **CA Fast Data Masker version 4.3**. So, if you have a version prior to this, stop now and update it to the last release, or this will not work at all.

Go to the FDM install folder and create a folder called `custom`.
```
Usually it's installed at C:\Program Files\Grid-Tools\FastDataMasker
```

Modify the provided the *custom_config.xml* and add the xml below inside the `<functions>` tag

```xml
<function>
  <name>HASHCNPJ</name>
  <description>HASHCNPJ - Consistently mask a given Brazilian CNPJ, retaining the original format of the number</description>
  <parm1></parm1>
  <parm2></parm2>
  <parm3></parm3>
  <parm4></parm4>
  <char>true</char>
  <number>true</number>
  <date>false</date>
  <char_date>false</char_date>
  <custom>true</custom>
  <class_name>com.ca.datamasker.custom.HashCNPJ</class_name>
</function>
```

The function will be available for Number and Character data types, It usually will be placed at the end of available functions with the name `HASHCNPJ`.


### Examples

Below you can find some examples, with the input CPNJ and how the function will mask it:
| Original CNPJ        | Masked CNPJ        | Obs                                                    |
| -------------------- |--------------------| -------------------------------------------------------|
| 85186183000134       | 27625621111610     | Masked value without Mask                              |
| 85.186.183/0001-34   | 27.625.621/1116-10 | Same input from above, with mask, same output with mask|
| 97.948.854/0001-36   | 21.212.271/1116-83 | The funcation can Hash CNPJ with it's mask             |
| *Null*               | *Null*             | Masking function will maintain Null values             |
| `Empty`              | `Empty`            | Masking function will maintain Empty values            |


## Built With

* [Java](https://www.oracle.com/technetwork/java/index.html) - Main language used to create this function.
* [FastDataMasker](https://docops.ca.com/ca-test-data-manager) - CA proprietary Data Masker library
* [Validator](https://commons.apache.org/proper/commons-validator/) - Validator library used by format hash
* [JUnit](https://junit.org/junit4/) - Testing framework
* [Graddle](https://gradle.org/) - Build and Dependency Management

## Contributing

Please read [CONTRIBUTING.md]([CONTRIBUTING.md]) for details on our code of conduct, and the process for submitting pull requests to me.

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/arturfigueira/HASHCNPJ/tags).

## Authors

*  [ArturFigueira](https://github.com/arturfigueira) - *Initial work*

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE.md](LICENSE.md) file for details.
