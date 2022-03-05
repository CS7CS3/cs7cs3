package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import lombok.Data;

import java.util.Optional;

@Data
public class GetHistoryResponse extends Validatable {

    public static GetHistoryResponse make (Optional<Journey> journeys){
        var res = new GetHistoryResponse(journeys);
        return res;
    }

    private GetHistoryResponse(Optional<Journey> journeys){
        this.journeys =journeys;
    }

    public Optional<Journey> journeys;

}
