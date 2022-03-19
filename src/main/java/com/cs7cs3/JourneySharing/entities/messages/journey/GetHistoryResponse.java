package com.cs7cs3.JourneySharing.entities.messages.journey;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
public class GetHistoryResponse extends Validatable {
    public static GetHistoryResponse make(List<Journey> journeys) {
        var res = new GetHistoryResponse(journeys);
        return res;
    }

    private GetHistoryResponse(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public List<Journey> journeys;

}
