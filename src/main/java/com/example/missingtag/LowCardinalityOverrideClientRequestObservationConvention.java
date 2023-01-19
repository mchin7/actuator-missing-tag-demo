package com.example.missingtag;

import io.micrometer.common.KeyValues;
import org.springframework.http.client.observation.ClientRequestObservationContext;
import org.springframework.http.client.observation.DefaultClientRequestObservationConvention;

public class LowCardinalityOverrideClientRequestObservationConvention extends DefaultClientRequestObservationConvention {

    @Override
    public KeyValues getLowCardinalityKeyValues(ClientRequestObservationContext context) {
        return KeyValues.of(uri(context), method(context), status(context), exception(context), clientName(context), outcome(context));
    }
}
