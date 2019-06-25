package com.client.logic;

public abstract class Calculations {

    /**
     * Total carbon calculation.
     *
     * @return transport + food + energy
     */
    public static double totalCarbon(FoodSurvey food, TransportationSurvey transport,
                                     EnergySurvey energy) {

        return transportTotal(transport) + foodTotal(food) + energy(energy);

    }

    /**
     * Total footprint calculation.
     *
     * @return transport + food + energy + life
     */
    public static double totalEco(FoodSurvey food, TransportationSurvey transport,
                                  EnergySurvey energy, LifeStyleSurvey life) {
        return footPrintFood(food) + footPrintTransport(transport)
                +
                footPrintEnergy(energy) + footPrintLife(life);

    }

    /**
     * Calculation of carbon emission based on food compost.
     *
     * @return double TotalCarbon
     */
    public static double foodCompost(FoodSurvey user) {
        double totalCarbon = 0;
        switch (user.amountFoodCompost) {
            case "None":
                //CO2 emissions for composting measured in tonnes of carbon.
                double noneCompost = 0.25;
                totalCarbon = totalCarbon + noneCompost;
                break;
            case "About a quarter":
                double quarterCompost = 0.1875;
                totalCarbon = totalCarbon + quarterCompost;
                break;
            case "Half":
                double halfCompost = 0.125;
                totalCarbon = totalCarbon + halfCompost;
                break;
            case "All food waste":
                double allCompost = 0;
                totalCarbon = totalCarbon + allCompost;
                break;

            default:
                totalCarbon = 0;
                break;

        }

        return totalCarbon;
    }

    /**
     * Calculation of carbon emission based on diet.
     *
     * @return double TotalCarbon
     */
    public static double foodDiet(FoodSurvey user) {
        double totalCarbon = 0;
        switch (user.dietChoice) {
            case "Vegan":
                //Emissions for diet
                double vegan = 0;
                totalCarbon = totalCarbon + vegan;
                break;
            case "Vegetarian":
                double vegetable = 0.1;
                totalCarbon = totalCarbon + vegetable;
                break;
            case "Low meat and dairy":
                double lessAvgMeat = 0.25;
                totalCarbon = totalCarbon + lessAvgMeat;
                break;
            case "Average meat and dairy":
                double averageMeat = 0.4;
                totalCarbon = totalCarbon + averageMeat;

                break;
            case "Heavy meat and dairy":
                double alotMeat = 0.6;
                totalCarbon = totalCarbon + alotMeat;
                break;

            default:
                totalCarbon = 0;
                break;

        }

        return totalCarbon;
    }

    /**
     * Carbon emission based on food locality.
     *
     * @return double Carbon total
     */
    public static double foodLocal(FoodSurvey user) {
        double totalCarbon = 0;
        switch (user.amountLocalFood) {
            case "Almost all":
                //Emissions for local food
                double mostLocal = 0.1;
                totalCarbon = totalCarbon + mostLocal;
                break;
            case "Above average":
                double aboveLocal = 0.2;
                totalCarbon = totalCarbon + aboveLocal;
                break;
            case "Average":
                double avgLocal = 0.3;
                totalCarbon = totalCarbon + avgLocal;
                break;
            case "Very little":
                double littleLocal = 0.5;
                totalCarbon = totalCarbon + littleLocal;
                break;

            default:
                totalCarbon = 0;
                break;
        }
        return totalCarbon;
    }

    /**
     * Carbon Emission based on waste.
     *
     * @return double
     */
    public static double foodTotal(FoodSurvey user) {
        double totalCarbon;
        switch (user.amountWastedFood) {
            case "Above average":
                //Emissions for waste, multiplier for whole food section.
                double aboveWaste = 1.1;
                totalCarbon = (foodCompost(user) + foodLocal(user) + foodDiet(user)
                        + foodOrganic(user) + foodPack(user)) * aboveWaste;

                break;
            case "Average":
                double avgWaste = 1;
                totalCarbon = (foodCompost(user) + foodLocal(user) + foodDiet(user)
                        + foodOrganic(user) + foodPack(user)) * avgWaste;

                break;
            case "Below average":
                double belowAvgWaste = 0.9;
                totalCarbon = (foodCompost(user) + foodLocal(user) + foodDiet(user)
                        + foodOrganic(user) + foodPack(user)) * belowAvgWaste;

                break;
            case "Very little":
                double littleWaste = 0.8;
                totalCarbon = (foodCompost(user) + foodLocal(user) + foodDiet(user)
                        + foodOrganic(user) + foodPack(user)) * littleWaste;
                break;

            default:
                totalCarbon = 0;
                break;

        }
        return totalCarbon;
    }

    /**
     * Carbon based on food packaging.
     *
     * @return double
     */
    public static double foodPack(FoodSurvey user) {
        double totalCarbon = 0;
        switch (user.amountFoodPackaged) {
            case "Above average":
                //Emissions for packaging
                double alotPackaging = 0.6;
                totalCarbon = totalCarbon + alotPackaging;
                break;
            case "Average":
                double avgPackaging = 0.4;
                totalCarbon = totalCarbon + avgPackaging;
                break;
            case "Below average":
                double lessPackaging = 0.2;
                totalCarbon = totalCarbon + lessPackaging;
                break;
            case "Very little":
                double littlePackaging = 0.05;
                totalCarbon = totalCarbon + littlePackaging;
                break;

            default:
                totalCarbon = 0;
                break;

        }
        return totalCarbon;

    }

    /**
     * Carbon based on amount of organic food.
     *
     * @return double
     */
    public static double foodOrganic(FoodSurvey user) {
        double totalCarbon = 0;
        switch (user.amountOrganicFood) {
            case "All":
                //Emissions for organic food
                double allOrganic = 0;
                totalCarbon = totalCarbon + allOrganic;
                break;
            case "Most":
                double mostOrganic = 0.2;
                totalCarbon = totalCarbon + mostOrganic;
                break;
            case "Some":
                double someOrganic = 0.5;
                totalCarbon = totalCarbon + someOrganic;
                break;
            case "None":
                double noneOrganic = 0.7;
                totalCarbon = totalCarbon + noneOrganic;
                break;
            default:
                totalCarbon = 0;
                break;
        }
        return totalCarbon;
    }

    /**
     * Method calculates carbon emissions due to air travel.
     *
     * @return double
     */
    public static double transportFly(TransportationSurvey user) {
        double carbon;
        // 0.25 tonnes of CO2 per hour of flight.
        double flightHour = 0.25;
        carbon = user.getAveHoursFly() * flightHour;

        return carbon;
    }

    /**
     * Method calculates total carbon emission due to transport.
     *
     * @return double
     */
    public static double transportTotal(TransportationSurvey user) {
        double carbon = 0;
        //Data for the transport section
        // CO2 released per kilometer traveled by car.
        // How many kilometers can cars of different sizes go per litre of fuel.
        double coLiter = 0.0024;
        if (user.getMainModeTransport().equals("Car")) {
            carbon = carCarbon(user);
        }
        if (user.getMainModeTransport().equals("Public transport")) {
            double publicTransport = 35;
            carbon = ((user.getAveKmPerDay() * 365) / publicTransport) * coLiter;
        }
        if (user.getMainModeTransport().equals("Walking/Biking")) {
            carbon = 0;
        }
        if (user.getMainModeTransport().equals("Motorcycle/Moped")) {
            double motorbike = 30;
            carbon = ((user.getAveKmPerDay() * 365) / motorbike) * coLiter;
        }
        if (!user.getTypeOfCar().equals("No Car") && !user.getMainModeTransport().equals("Car")) {
            carbon = carbon + 0.2;

        }
        return carbon + transportFly(user);

    }

    /**
     * Stuff.
     *
     * @param user transportation survey.
     * @return double
     */
    public static double carCarbon(TransportationSurvey user) {
        double carbon = 0;
        double coLiter = 0.0024;
        switch (user.getTypeOfCar()) {
            case "Small car":
                int smallCar = 18;
                carbon = ((user.getAveKmPerDay() * 365.0) / smallCar) * coLiter;

                break;
            case "Medium car":
                int medCar = 15;
                carbon = ((user.getAveKmPerDay() * 365.0) / medCar) * coLiter;

                break;
            case "Large car":
                int bigCar = 10;
                carbon = ((user.getAveKmPerDay() * 365.0) / bigCar) * coLiter;

                break;
            case "Electric car":
                int electricCar = 34;
                carbon = ((user.getAveKmPerDay() * 365.0) / electricCar) * coLiter;

                break;
            case "No car":
                carbon = 0;
                break;

            default:
                return 0;

        }
        return carbon;
    }

    /**
     * Carbon based on energy usage.
     *
     * @return double
     */
    public static double energy(EnergySurvey user) {
        double carbon = 0;
        double greenEnergy = 0.2;//Multipliers for GreenStreak energy and efficient lighting
        double efficientLight = 0.85;
        if (user.typeHouse.equals("Apartment")) {
            //Types of houses and kWh for each
            double apartment = 3000;
            if (user.efficientLighting) {
                carbon = apartment * efficientLight;
            }
            if (user.greenEnergy) {
                carbon = carbon * greenEnergy;
            } else {
                carbon = apartment;
            }
        }
        if (user.typeHouse.equals("Small house")) {
            double smallHouse = 3500;
            if (user.efficientLighting) {
                carbon = smallHouse * efficientLight;
            }
            if (user.greenEnergy) {
                carbon = carbon * greenEnergy;
            } else {
                carbon = smallHouse;
            }

        } else {
            carbon = mediumLarge(user);
        }
        double electricity = 0.00034;
        return (carbon * electricity) / user.getAmountRoommates();
    }

    /**
     * .
     *
     * @param user stuff
     * @return double
     */
    public static double mediumLarge(EnergySurvey user) {
        double carbon = 0;
        double greenEnergy = 0.2;//Multipliers for GreenStreak energy and efficient lighting
        double efficientLight = 0.85;
        if (user.typeHouse.equals("Medium house")) {
            double medHouse = 4800;
            if (user.efficientLighting) {
                carbon = medHouse * efficientLight;
            }
            if (user.greenEnergy) {
                carbon = carbon * greenEnergy;
            } else {
                carbon = medHouse;
            }
        }
        if (user.typeHouse.equals("Large house")) {
            double largeHouse = 7000;
            if (user.efficientLighting) {
                carbon = largeHouse * efficientLight;
            }
            if (user.greenEnergy) {
                carbon = carbon * greenEnergy;
            } else {
                carbon = largeHouse;
            }
        }
        return carbon;

    }


    /**
     * Footprint based on diet choice.
     *
     * @return double footprint
     */
    public static double footPrintFood(FoodSurvey food) {
        double foot = 0;
        switch (food.dietChoice) {
            case "Vegan":
                foot = foot + 0.07;
                break;
            case "Vegetarian":
                foot = foot + 0.15;
                break;
            case "Low meat and dairy":
                foot = foot + 0.3;
                break;
            case "Average meat and dairy":
                foot = foot + 0.4;
                break;
            case "Heavy meat and dairy":
                foot = foot + 0.5;
                break;
            default:
                return 0;
        }
        switch (food.amountOrganicFood) {
            case "All":
                foot = foot + 0.05;
                break;
            case "Most":
                foot = foot + 0.15;
                break;
            case "Some":
                foot = foot + 0.2;
                break;
            case "None":
                foot = foot + 0.3;
                break;
            default:
                foot = 0;
                break;
        }
        return foot;
    }

    /**
     * Footprint based on transport.
     *
     * @return Footprint
     */
    public static double footPrintTransport(TransportationSurvey transport) {
        double foot = 0;
        if (transport.getMainModeTransport().equals("Car")) {
            foot = carFoot(transport);

        } else {
            switch (transport.getMainModeTransport()) {
                case "Public transport":
                    foot = (transport.getAveKmPerDay() * 365.0) / 12000;
                    break;
                case "Walking/Biking":
                    foot = 0;
                    break;
                case "Motorcycle/Moped":
                    foot = (transport.getAveKmPerDay() * 365.0) / 11500;
                    break;
                default:
                    foot = 0;
                    break;
            }
        }


        if (!transport.getTypeOfCar().equals("No car")
                && !transport.getMainModeTransport().equals("car")) {

            foot = foot + 0.1;
        }

        return foot + (transport.getAveHoursFly() * 0.1);
    }

    /**
     * Stuff.
     *
     * @param transport stuff
     * @return double
     */
    public static double carFoot(TransportationSurvey transport) {
        double foot = 0;
        switch (transport.getTypeOfCar()) {
            case "Small car":
                foot = (transport.getAveKmPerDay() * 365.0) / 10000;
                break;
            case "Medium car":
                foot = (transport.getAveKmPerDay() * 365.0) / 9000;
                break;
            case "Large car":
                foot = (transport.getAveKmPerDay() * 365.0) / 8000;
                break;
            case "Electric car":
                foot = (transport.getAveKmPerDay() * 365.0) / 12000;
                break;
            case "No car":
                break;
            default:
                foot = 0;
                break;
        }
        return foot;
    }

    /**
     * Footprint based on energy usage.
     *
     * @return footprint
     */
    public static double footPrintEnergy(EnergySurvey energy) {
        double foot = 0;
        switch (energy.amountEnergy) {
            case "Below average":
                foot = foot + 0.8;
                break;
            case "Average":
                foot = foot + 0.5;
                break;
            case "Above average":
                foot = foot + 0.25;
                break;

            default:
                foot = 0;
                break;
        }
        if (energy.greenEnergy) {
            foot = foot + 0.07;
        } else {
            foot = foot + 0.5;
        }
        return foot;
    }

    /**
     * Footprint based on lifestyle choices.
     *
     * @return footprint
     */
    public static double footPrintLife(LifeStyleSurvey life) {
        double foot = 0;
        switch (life.howOftenBigPurchase) {
            case "More than 5":
                foot = foot + 2;
                break;
            case "Around 2 - 4":
                foot = foot + 1.5;
                break;
            case "Maybe once":
                foot = foot + 0.5;
                break;
            case "Very rarely":
                foot = foot + 0.1;
                break;
            default:
                foot = 0;
                break;
        }
        switch (life.weeklyWaste) {
            case "Less than 15kg":
                foot = foot + 0.1;
                break;
            case "15kg - 30kg":
                foot = foot + 0.3;
                break;
            case "30kg - 45kg":
                foot = foot + 0.75;
                break;
            case "45kg - 60kg":
                foot = foot + 1.5;
                break;
            case "More than 60kg":
                foot = foot + 3;
                break;
            default:
                foot = 0;
                break;

        }
        foot = foot + footAppRec(life);

        return foot + (life.aveShowerTime * 0.01);

    }

    /**
     * .
     *
     * @param life stuuf
     * @return double
     */
    public static double footAppRec(LifeStyleSurvey life) {
        double foot = 0;
        switch (life.recycle) {
            case "Glass":
                foot = foot - 0.1;
                break;
            case "Paper":
                foot = foot - 0.2;
                break;
            case "Plastic":
                foot = foot - 0.3;
                break;
            case "Metal":
                foot = foot - 0.4;
                break;

            default:
                foot = 0;
                break;

        }

        switch (life.applianceUse) {
            case "10x a week":
                foot = foot + 1;
                break;
            case "5 - 10x a week":
                foot = foot + 0.5;
                break;
            case "1 - 5x a week":
                foot = foot + 0.3;
                break;
            case "Not applicable":
                foot = foot + 0;
                break;

            default:
                foot = 0;
                break;
        }
        return foot;
    }

}



