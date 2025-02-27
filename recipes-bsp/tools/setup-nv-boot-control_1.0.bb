DESCRIPTION = "Script and systemd service to set up the boot control \
configuration file needed for tegra redundant boot support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
    file://uefi_common.func.in \
"

COMPATIBLE_MACHINE = "(tegra)"

ESPMOUNT ?= "/tmp/esp"
ESPMOUNTUNIT ?= "${@'-'.join(d.getVar('ESPMOUNT').split('/')[1:])}.mount"

S = "${WORKDIR}"

inherit systemd update-rc.d

do_compile() {
    sed -e 's,@ESPMOUNT@,${ESPMOUNT},g' ${S}/uefi_common.func.in >${B}/uefi_common.func
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/uefi_common.func ${D}${bindir}/
}

pkg_postinst:${PN}() {
    if [ ! -d $D${systemd_system_unitdir}/local-fs.target.wants ]; then
        mkdir -p $D${systemd_system_unitdir}/local-fs.target.wants
    fi
    ln -sf ../${ESPMOUNTUNIT} $D${systemd_system_unitdir}/local-fs.target.wants/
}

PACKAGES =+ "${PN}-service"
INITSCRIPT_NAME = "setup-nv-boot-control"
INITSCRIPT_PARAMS = "defaults 12"
RDEPENDS:${PN} = "efivar tegra-nv-boot-control-config tegra-eeprom-tool-boardspec"

FILES:${PN} = "\
    ${bindir}/uefi_common.func \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
