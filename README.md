# TheTransactionAnalyzer

Task: the system will be Initialised with an input file in CSV format containing a list of transaction records. Once initialised, the system should report the total number of transactions and the average transaction value for a specific merchant in a specific date range. An additional requirement is that, if a transaction record has a REVERSAL transaction, then it should not be included in the computed statistics, even if the reversing transaction is outside of the requested date range.

To start the program you need to start method main in ProgramStart.class.

At the beggining of the file you can change input parameters (including path to file and it's name) to process it with your own.

Input .csv-file is located at .\src\main\resources
