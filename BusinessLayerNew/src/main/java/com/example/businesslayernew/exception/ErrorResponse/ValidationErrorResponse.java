package com.example.businesslayernew.exception.ErrorResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();

}
