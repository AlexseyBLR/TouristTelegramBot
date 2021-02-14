package com.meleschenya.tourist_telegram_bot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city_description", schema = "touristtelegrambot")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Getter
    @Column(name = "name")
    private String name;

    @Getter
    @Column(name = "description")
    private String description;

    @Getter
    @Column(name = "country")
    private String country;

}
