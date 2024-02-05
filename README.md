# UCUM-FHIR OpenRefine GREL functions

This repository is a extension that adds some of the functionality provided by
the [UCUM-Java](https://github.com/FHIR/Ucum-java/tree/master) library to OpenRefine.

## Added GREL functions

### `ucumConvert(value: string, from: string, to: string)`

Converts a value from one unit to another.

Returns the converted value as a string.

```grel
ucumConvert("1.0", "mol/L", "mmol/L")
```

### `ucumSearch(concept: prefix|base|unit|null, search: string, isRegex: true|false(string))`

Searches for a UCUM concept.

Returns most relevant UCUM concept as a string. If no concept is found, returns null.

```grel
ucumSearch("unit", "mol", "false")
```

### `ucumValidate(unit: string)`

Validates a UCUM unit.

Returns true if the unit is valid, false otherwise.

```grel
ucumValidate("mol/L")
```

## How to install

1. Download the latest release from the releases page.
2. Drop the folder inside the zip file into the `extensions` folder of your OpenRefine installation.
   See [this page](https://openrefine.org/docs/manual/installing#find-the-right-place-to-install)
3. Restart/Start OpenRefine.

