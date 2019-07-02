package it.polimi.se2019.view.client.cli;

/**
 * Class uses to manage the status of the cli and the sequence of actions as a state machine.
 *
 * @author Simone Orlando
 */
public enum CliState {
    ACTIONSELECTION,
    POWERUPCONVERTING,
    POWERUPCHOOSING,
    WEAPONSCHOOSING,
    PLATFORMSCHOOSING,
    WEAPONSWITCHING,
    TARGETSHOWING,
    EFFECTSSHOWING,
    OPTIONSHOWING,
    WEAPONSRELOADING,
    WEAPONSUSING,
    POWERUPSUSING,
    AMMODISCARTING
}
