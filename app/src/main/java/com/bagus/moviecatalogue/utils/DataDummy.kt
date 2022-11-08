package com.bagus.moviecatalogue.utils

import com.bagus.moviecatalogue.data.source.local.entity.MovieEntity
import com.bagus.moviecatalogue.data.source.local.entity.TvshowEntity
import com.bagus.moviecatalogue.data.source.remote.response.MoviesResponse
import com.bagus.moviecatalogue.data.source.remote.response.TvshowResponse

object DataDummy {

    fun getDetailMovie(): MovieEntity {
        return MovieEntity(
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "02/14/2019",
            6.5,
            1,
            "Alita Battle Angel",
            "/asasasasasasa",
            false
        )
    }

    fun generateDummyMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "02/14/2019",
                6.5,
            1,
            "Alita Battle Angel",
            "/asasasasasasa",
                false
            )
        )
        movies.add(
            MovieEntity(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "12/26/2018",
                6.5,
                2,
                "Aquaman",
                "poster_aquaman",
                false
            ))
        movies.add(
            MovieEntity(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "10/30/2018",
                6.5,
                3,
                "Bohemian Rhapsody",
                "poster_bohemian",
                false
            ))
        movies.add(
            MovieEntity(
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "11/25/2015",
                6.5,
                4,
                "Creed",
                "poster_creed",
                false
            ))
        movies.add(
            MovieEntity(
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "11/16/2018",
                6.5,
                5,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "poster_crimes",
                false
            ))
        movies.add(
            MovieEntity(
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "04/27/2018",
                6.5,
                6,
                "Avengers: Infinity War",
                "poster_infinity_war",
                false
            ))
        movies.add(
            MovieEntity(
                "Wreck-It Ralph is the 9-foot-tall, 643-pound villain of an arcade video game named Fix-It Felix Jr., in which the game's titular hero fixes buildings that Ralph destroys. Wanting to prove he can be a good guy and not just a villain, Ralph escapes his game and lands in Hero's Duty, a first-person shooter where he helps the game's hero battle against alien invaders. He later enters Sugar Rush, a kart racing game set on tracks made of candies, cookies and other sweets. There, Ralph meets Vanellope von Schweetz who has learned that her game is faced with a dire threat that could affect the entire arcade, and one that Ralph may have inadvertently started.",
                "11/02/2012",
                6.5,
                7,
                "Wreck-It Ralph",
                "poster_ralph",
                false
            ))
        movies.add(
            MovieEntity(
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "11/21/2018",
                6.5,
                8,
                "Robin Hood",
                "poster_robin_hood",
                false
            ))
        movies.add(
            MovieEntity(
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "01/25/2019",
                6.5,
                9,
                "Serenity",
                "poster_serenity",
                false
            ))
        movies.add(
            MovieEntity(
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "12/14/2018",
                6.5,
                10,
                "Spider-Man: Into the Spider-Verse",
                "poster_spiderman",
                false
            ))

        return movies
    }

    fun getDetailTvshow(): TvshowEntity {
        return TvshowEntity(
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "10/10/2012",
            6.5,
            233211,
            "Arrow",
            "/aaaaaaa",
            false
        )
    }

    fun generateDummyTvshow(): List<TvshowEntity> {
        val tvshow = ArrayList<TvshowEntity>()

        tvshow.add(
            TvshowEntity(
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "10/10/2012",
                6.5,
                233211,
                "Arrow",
                "/aaaaaaa",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "02/15/2019",
                6.5,
                1,
                "Doom Patrol",
                "poster_doom_patrol",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "10/07/2014",
                6.5,
                3,
                "The Flash",
                "poster_flash",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "04/17/2011",
                6.5,
                4,
                "Game of Thrones",
                "poster_god",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "09/22/2014",
                6.5,
                5,
                "Gotham",
                "poster_gotham",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "03/17/2017",
                6.5,
                6,
                "Marvel's Iron Fist",
                "poster_iron_fist",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "01/26/2017",
                6.5,
                7,
                "Riverdale",
                "poster_riverdale",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "10/26/2015",
                6.5,
                8,
                "Supergirl",
                "poster_supergirl",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "02/15/2019",
                6.5,
                9,
                "The Umbrella Academy",
                "poster_the_umbrella",
                false
            )
        )
        tvshow.add(
            TvshowEntity(
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "10/31/2010",
                6.5,
                10,
                "The Walking Dead",
                "poster_the_walking_dead",
                false
            )
        )

        return tvshow
    }

    fun generateRemoteDummyMovies(): List<MoviesResponse> {
        val movies = ArrayList<MoviesResponse>()

        movies.add(
            MoviesResponse(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "02/14/2019",
                6.5,
                1,
                "Alita Battle Angel",
                "/asasasasasasa",
            )
        )
        movies.add(
            MoviesResponse(
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "12/26/2018",
                6.5,
                2,
                "Aquaman",
                "poster_aquaman"
            ))
        movies.add(
            MoviesResponse(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "10/30/2018",
                6.5,
                3,
                "Bohemian Rhapsody",
                "poster_bohemian"
            ))
        movies.add(
            MoviesResponse(
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "11/25/2015",
                6.5,
                4,
                "Creed",
                "poster_creed"
            ))
        movies.add(
            MoviesResponse(
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "11/16/2018",
                6.5,
                5,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "poster_crimes"
            ))
        movies.add(
            MoviesResponse(
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "04/27/2018",
                6.5,
                6,
                "Avengers: Infinity War",
                "poster_infinity_war"
            ))
        movies.add(
            MoviesResponse(
                "Wreck-It Ralph is the 9-foot-tall, 643-pound villain of an arcade video game named Fix-It Felix Jr., in which the game's titular hero fixes buildings that Ralph destroys. Wanting to prove he can be a good guy and not just a villain, Ralph escapes his game and lands in Hero's Duty, a first-person shooter where he helps the game's hero battle against alien invaders. He later enters Sugar Rush, a kart racing game set on tracks made of candies, cookies and other sweets. There, Ralph meets Vanellope von Schweetz who has learned that her game is faced with a dire threat that could affect the entire arcade, and one that Ralph may have inadvertently started.",
                "11/02/2012",
                6.5,
                7,
                "Wreck-It Ralph",
                "poster_ralph"
            ))
        movies.add(
            MoviesResponse(
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "11/21/2018",
                6.5,
                8,
                "Robin Hood",
                "poster_robin_hood"
            ))
        movies.add(
            MoviesResponse(
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "01/25/2019",
                6.5,
                9,
                "Serenity",
                "poster_serenity"
            ))
        movies.add(
            MoviesResponse(
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "12/14/2018",
                6.5,
                10,
                "Spider-Man: Into the Spider-Verse",
                "poster_spiderman"
            ))

        return movies
    }

    fun generateRemoteDummyTvshows(): List<TvshowResponse> {
        val tvshow = ArrayList<TvshowResponse>()

        tvshow.add(
            TvshowResponse(
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "10/10/2012",
                6.5,
                233211,
                "Arrow",
                "/aaaaaaa",
            )
        )
        tvshow.add(
            TvshowResponse(
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "02/15/2019",
                6.5,
                1,
                "Doom Patrol",
                "poster_doom_patrol"
            )
        )
        tvshow.add(
            TvshowResponse(
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "10/07/2014",
                6.5,
                3,
                "The Flash",
                "poster_flash"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "04/17/2011",
                6.5,
                4,
                "Game of Thrones",
                "poster_god"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "09/22/2014",
                6.5,
                5,
                "Gotham",
                "poster_gotham"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "03/17/2017",
                6.5,
                6,
                "Marvel's Iron Fist",
                "poster_iron_fist"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "01/26/2017",
                6.5,
                7,
                "Riverdale",
                "poster_riverdale"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "10/26/2015",
                6.5,
                8,
                "Supergirl",
                "poster_supergirl"
            )
        )
        tvshow.add(
            TvshowResponse(
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "02/15/2019",
                6.5,
                9,
                "The Umbrella Academy",
                "poster_the_umbrella"
            )
        )
        tvshow.add(
            TvshowResponse(
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "10/31/2010",
                6.5,
                10,
                "The Walking Dead",
                "poster_the_walking_dead"
            )
        )

        return tvshow
    }
}