package ooga.controller;

import java.util.List;

public record LoadBoardRecord (int rows, int columns, List<ParsedProperty> places){
}
