DESCRIPTION = "Minimal initramfs init script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
    file://init-boot.sh \
    file://platform-preboot.sh \
"

S = "${WORKDIR}"

do_install() {
    install -m 0755 ${WORKDIR}/init-boot.sh ${D}/init
    install -d ${D}/proc ${D}/sys ${D}/dev ${D}/tmp ${D}/mnt ${D}/run ${D}/usr
    mknod -m 622 ${D}/dev/console c 5 1
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/platform-preboot.sh ${D}${sysconfdir}/platform-preboot
}

RDEPENDS_${PN} = "${VIRTUAL-RUNTIME_base-utils}"
RDEPENDS_${PN}_append_tegra194 = " util-linux-blkid"
FILES_${PN} = "/"

PACKAGE_ARCH = "${MACHINE_ARCH}"
