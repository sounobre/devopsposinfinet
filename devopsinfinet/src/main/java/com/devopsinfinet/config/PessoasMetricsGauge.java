package com.devopsinfinet.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;

@Component
public class PessoasMetricsGauge implements MeterBinder {

	@Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("Pessoas", this, value -> obterPessoas())
                .description("Pessoas")
                .tags(Tags.of(Tag.of("data", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .baseUnit("Diego") 
                .register(meterRegistry);
    }

    public Integer obterPessoas() {
       return new Random().nextInt(8);
    }
}
