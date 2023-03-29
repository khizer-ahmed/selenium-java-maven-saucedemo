package com.example.wallethub.data;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseData {
    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;

    @Parsed(field = "Test Case Description", defaultNullRead = "")
    private String testCaseDescription;
}