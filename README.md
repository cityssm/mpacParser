# mpacParser
MPAC Year End Tax File Parser

The Year End Tax Files provided to municipalities by [MPAC](https://www.mpac.ca/) have a *unique* format to say the least.
This project attempts to simplify using these files by converting them to more friendly formats.
CSV output is provided, however the project can quickly be extended to output the data to your preferred database.

## Getting Started

The project takes an MPAC Year End Tax File as input.
MPAC provides these files to municipalities on a DVD, usually in the month of November.
The necessary file will have a name like `Xnnnnnn_YETF_MMMYYYY.txt`.
It may need to be extracted from an encrypted archive first.

The basic code is as follows:

```java
// Point to the MPAC YETF.
File f = new File("C:\\mpacParser\\Xnnnnnn_YETF_MMMYYYY.txt");

// Create a new parser, pointing at the MPAC YETF.
MPACParser parser = new MPACParser(f);
	
// Add any outputters that should be used.
// This is where you could create a specific outputter for your database.
parser.registerOutputter(new CSVOutputter(new File("C:\\mpacParser\\outputFolderName")));

// Parse the file
try {
    parser.parseFile();
} catch (Exception e) {
    e.printStackTrace();
}
```